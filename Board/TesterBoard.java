package romine.colorwheel.Board;

import java.util.ArrayList;

import romine.colorwheel.Pieces.AtomicGamePiece;
import romine.colorwheel.Pieces.BasePiece;
import romine.colorwheel.Pieces.GamePiece;

/**
 * Created by karom on 10/22/2016.
 */

public class TesterBoard extends NoUpdateBoard {

    //add color counts improvement later if needed after seeing speed

    private NoUpdateBoard duplicate;

    public TesterBoard(ArrayList<BasePiece> solution, int boardDimension) {
        super(0, 0, 0, solution, boardDimension);
        duplicate = new NoUpdateBoard(0, 0, 0, new ArrayList<BasePiece>(piecesOnBoard), boardDimension);
    }

    public ArrayList<BasePiece> getRelevantPieces() {
        BasePiece piece;
        for (int i = 0; i < piecesOnBoard.size(); ) {
            piece = piecesOnBoard.get(i);
            if (!removeIfNoDifference(piece, piece.getXOffset(), piece.getYOffset())) {
                i++;
            }
        }
        return piecesOnBoard;
    }

    private boolean removeIfNoDifference(BasePiece piece, int xOffset, int yOffset) {
        if (pieceMakesDifference(piece, piece.getXOffset(), piece.getYOffset())) {
            return false;
        } else {
            removePiece(piece, piece.getXOffset(), piece.getYOffset());
            return true;
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

}
