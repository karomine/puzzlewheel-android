package romine.colorwheel.Board;

import java.util.ArrayList;

import romine.colorwheel.Pieces.AtomicGamePiece;
import romine.colorwheel.Pieces.BasePiece;
import romine.colorwheel.Pieces.GamePiece;

/**
 * Created by karom on 10/22/2016.
 */

public class TesterBoard extends NoUpdateBoard {

    private NoUpdateBoard duplicate;

    public TesterBoard(ArrayList<BasePiece> solution, int boardDimension) {
        super(0, 0, 0, solution, boardDimension);
        duplicate = new NoUpdateBoard(0, 0, 0, new ArrayList<>(piecesOnBoard), boardDimension);
    }

    public void addPiece(BasePiece piece, int xOffset, int yOffset) {
        super.addPiece(piece, xOffset, yOffset);
        duplicate.addPiece(piece, xOffset, yOffset);
    }

    public void removePiece(BasePiece piece, int xOffset, int yOffset) {
        super.removePiece(piece, xOffset, yOffset);
        duplicate.removePiece(piece, xOffset, yOffset);
    }

    public void removeIrrelevantPieces() {
        BasePiece piece;
        for (int i = 0; i < numPieces(); ) {
            piece = piecesOnBoard.get(i);
            if (!pieceMakesDifference(piece, piece.getXOffset(), piece.getYOffset())) {
                removePiece(piece, piece.getXOffset(), piece.getYOffset());
            } else {
                i++;
            }
        }
    }

    private boolean pieceMakesDifference(GamePiece piece, int xOffset, int yOffset) {
        if (piece instanceof BasePiece) {
            for (GamePiece innerPiece : piece.getPieces()) {
                if (pieceMakesDifference(innerPiece, xOffset + innerPiece.getXOffset(), yOffset + innerPiece.getYOffset())) {
                    return true;
                }
            }
            return false;
        } else {
            boolean match;
            AtomicGamePiece atomicPiece = (AtomicGamePiece) piece;
            int[][] sections = atomicPiece.getSections();
            boardGrid[xOffset][yOffset].setColorsInRanges(sections, atomicPiece);
            match = boardGrid[xOffset][yOffset].matchInRanges(sections, duplicate.getBoardGrid()[xOffset][yOffset]);
            boardGrid[xOffset][yOffset].setColorsInRanges(sections);
            return !match;
        }
    }

    public int numPieces() {
        return piecesOnBoard.size();
    }

    public ArrayList<BasePiece> getPieces() {
        return piecesOnBoard;
    }

}
