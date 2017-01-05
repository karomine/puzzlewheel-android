package romine.colorwheel;

import java.util.ArrayList;

import romine.colorwheel.Board.TesterBoard;
import romine.colorwheel.Pieces.BasePiece;
import romine.colorwheel.Pieces.GamePiece;
import romine.colorwheel.Pieces.Offset;
import romine.colorwheel.Pieces.PieceGenerator;

/**
 * Created by karom on 10/23/2016.
 */

public class PuzzleGenerator {

    private final static int MAX_PASSES = 50;
    private int mCurrentPasses;
    private int mBoardDimension;
    private int mDifficulty;
    private int mNumPieces;
    private PieceGenerator mPieceGenerator;
    private static PieceGenerator mClearPieceGenerator = new PieceGenerator(4, 0, 1, 0);
    private int mRequiredClear;
    private ArrayList<BasePiece> mPieces;
    private TesterBoard mTesterBoard;

    PuzzleGenerator(int boardDimension, int difficulty) {
        this.mBoardDimension = boardDimension;
        this.mBoardDimension = difficulty;
        createPieceGenerator();
    }

    private void createPieceGenerator() {
        switch (mDifficulty) {
            case 1:
                setPieceGenerator(1, 0, 0, 1, 0);
                break;
            case 2:
                setPieceGenerator(1, 0, 0, 2, 0);
                break;
            case 3:
                setPieceGenerator(.9, 0, 0, 3.5, 0);
                break;
            case 4:
                setPieceGenerator(.64, .16, .1, 2, .5);
                break;
            case 5:
                setPieceGenerator(.64, .16, .15, 2.5, .5);
                break;
            case 6:
                setPieceGenerator(.64, .16, .15, 3.25, .75);
                break;
            case 7:
                setPieceGenerator(.62, .18, .2, 4, .75);
                break;
            case 8:
                setPieceGenerator(.62, .18, .2, 5, .75);
                break;
            case 9:
                setPieceGenerator(.6, .2, .25, 6.25, .75);
                break;
            case 10:
                setPieceGenerator(.6, .2, .25, 7.5, .75);
                break;
        }
    }

    private void setPieceGenerator(double regularProp, double clearProp, double multiClearProp,
                                   double numPiecesScalar, double numClearScalar) {
        mPieceGenerator = new PieceGenerator(mBoardDimension, regularProp, clearProp, multiClearProp);
        mNumPieces = (int) Math.floor(numPiecesScalar * mBoardDimension);
        mRequiredClear = (int) Math.floor(numClearScalar * mBoardDimension);
    }

    public Puzzle generatePuzzle() {
        BasePiece piece;
        mPieces = new ArrayList<BasePiece>();
        mCurrentPasses = 0;

        while (mPieces.size() < mNumPieces) {
            piece = mPieceGenerator.generatePiece();
            piece.setOffsets(getRandomOffset(piece.getXDimension(), piece.getYDimension()));
            mPieces.add(piece);
            if (mPieces.size() == mNumPieces) {
                checkForClears();
                removeUselessPieces();
                if (mPieces.size() == mNumPieces) {
                    if (!mTesterBoard.pieceOverlap(0, 0, mBoardDimension - 1, mBoardDimension - 1)) {
                        mPieces = new ArrayList<BasePiece>();
                    }
                }
            }
        }
        return new Puzzle(mBoardDimension, mPieces);
    }

    private void checkForClears() {
        int neededClears = getClearsNeeded();
        if (neededClears > 0) {
            addClears(neededClears);
        }
    }

    private int getClearsNeeded() {
        int needed = mRequiredClear;
        int i = 0;
        while (needed > 0 && i < mPieces.size()) {
            if (mPieces.get(i).hasColor(GamePiece.PieceColor.CLEAR)) {
                needed--;
            }
            i++;
        }
        return needed;
    }

    private void addClears(int num) {
        BasePiece piece;
        int chance = Rand.randomRange(0, mPieces.size() - 1);
        while (num > 0) {
            piece = mClearPieceGenerator.generatePiece();
            piece.setOffsets(getRandomOffset(piece.getXDimension(), piece.getYDimension()));
            while (mPieces.get(chance).hasColor(GamePiece.PieceColor.CLEAR)) {
                chance = Rand.randomRange(0, mPieces.size() - 1);
            }
            mPieces.set(chance, piece);
            num--;
        }
    }

    private void removeUselessPieces() {
        if (mCurrentPasses > MAX_PASSES) {
            mPieces = new ArrayList<>();
            mCurrentPasses = 0;
        } else {
            mTesterBoard = new TesterBoard(mPieces, mBoardDimension);
            mPieces = mTesterBoard.getRelevantPieces();
            mCurrentPasses++;
        }
    }

    private Offset getRandomOffset(int xDimension, int yDimension) {
        return new Offset(Rand.randomRange(0, mBoardDimension - xDimension),
                Rand.randomRange(0, mBoardDimension - yDimension));
    }
}
