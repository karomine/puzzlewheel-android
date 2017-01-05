package romine.colorwheel.Game;

import java.util.ArrayList;

import romine.colorwheel.Board.TesterBoard;
import romine.colorwheel.Pieces.BasePiece;
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
    private int mRequiredClear;
    private TesterBoard mTesterBoard;

    PuzzleGenerator(int boardDimension, int difficulty) {
        setSettings(boardDimension, difficulty);
    }

    public void setSettings(int boardDimension, int difficulty) {
        mBoardDimension = boardDimension;
        mDifficulty = difficulty;
        createPieceGenerator();
    }

    private void createPieceGenerator() {
        switch (mDifficulty) {
            case 1:
                setPieceGenerator(1, 1, 0);
                break;
            case 2:
                setPieceGenerator(1, 2, 0);
                break;
            case 3:
                setPieceGenerator(1, 3.5, 0);
                break;
            case 4:
                setPieceGenerator(.84, 2, .5);
                break;
            case 5:
                setPieceGenerator(.84, 2.5, .5);
                break;
            case 6:
                setPieceGenerator(.84, 3.25, .75);
                break;
            case 7:
                setPieceGenerator(.82, 4, .75);
                break;
            case 8:
                setPieceGenerator(.82, 5, .75);
                break;
            case 9:
                setPieceGenerator(.8, 6.25, .75);
                break;
            case 10:
                setPieceGenerator(.8, 7.5, .75);
                break;
        }
    }

    private void setPieceGenerator(double regularProp, double numPiecesScalar, double numClearScalar) {
        mPieceGenerator = new PieceGenerator(regularProp);
        mNumPieces = (int) Math.floor(numPiecesScalar * mBoardDimension);
        mRequiredClear = (int) Math.floor(numClearScalar * mBoardDimension);
    }

    public Puzzle generatePuzzle() {
        BasePiece piece;
        mCurrentPasses = 0;
        newTesterBoard();

        while (mTesterBoard.numPieces() < mNumPieces) {
            for (int i = mTesterBoard.numPieces(); i < mNumPieces; i++) {
                piece = mPieceGenerator.generatePiece();
                piece.setOffsets(getRandomOffset(piece.getXDimension(), piece.getYDimension()));
                mTesterBoard.addPiece(piece, piece.getXOffset(), piece.getYOffset());
            }
            checkForClears();
            removeUselessPieces();
            if (mTesterBoard.numPieces() == mNumPieces) {
                if (!mTesterBoard.pieceOverlap(0, 0, mBoardDimension - 1, mBoardDimension - 1)) {
                    newTesterBoard();
                }
            }
        }
        return new Puzzle(mBoardDimension, mTesterBoard.getPieces());
    }

    private void checkForClears() {
        int neededClears = getClearsNeeded();
        if (neededClears > 0) {
            newTesterBoard();
        }
    }

    private int getClearsNeeded() {
        ArrayList<BasePiece> pieces = mTesterBoard.getPieces();
        int needed = mRequiredClear;
        int i = 0;
        while (needed > 0 && i < pieces.size()) {
            if (pieces.get(i).isClear()) {
                needed--;
            }
            i++;
        }
        return needed;
    }

    private void removeUselessPieces() {
        if (mCurrentPasses > MAX_PASSES) {
            newTesterBoard();
            mCurrentPasses = 0;
        } else {
            mTesterBoard.removeIrrelevantPieces();
            mCurrentPasses++;
        }
    }

    private Offset getRandomOffset(int xDimension, int yDimension) {
        return new Offset(Rand.randomRange(0, mBoardDimension - xDimension - 1),
                Rand.randomRange(0, mBoardDimension - yDimension - 1));
    }

    private void newTesterBoard() {
        mTesterBoard = new TesterBoard(new ArrayList<BasePiece>(), mBoardDimension);
    }
}
