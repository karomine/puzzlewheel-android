package romine.colorwheel.Board;

import android.graphics.Canvas;

import java.util.ArrayList;

import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 10/22/2016.
 */

public class SolutionBoard extends GameBoard {

    SolutionBoard(float xOffset, float yOffset, float scale, ArrayList<BasePiece> solution, int boardDimension, Canvas canvas) {
        super(xOffset, yOffset, scale, boardDimension, canvas);
        for (BasePiece piece : solution) {
            addPiece(piece, piece.getXOffset(), piece.getYOffset());
        }
    }

    public boolean match(Board other) {
        for (int i = 0; i < boardGrid.length; i++) {
            for (int j = 0; j < boardGrid[i].length; j++) {
                if (!boardGrid[i][j].match(other.getBoardGrid()[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

}
