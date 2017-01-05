package romine.colorwheel.Board;

import android.graphics.Canvas;

import java.util.ArrayList;

import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 10/22/2016.
 */

public abstract class MultiBoards extends Board {

    MultiBoards(float xOffset, float yOffset, float scale, Canvas canvas) {
        super(xOffset, yOffset, scale, canvas);
    }

    int getBoardDimension() {
        return getBoard().getBoardDimension();
    }
    ArrayList<BasePiece> getPiecesOnBoard() {
        return getBoard().getPiecesOnBoard();
    }
    public GridTile[][] getBoardGrid() {
        return getBoard().getBoardGrid();
    }
}
