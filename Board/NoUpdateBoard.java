package romine.colorwheel.Board;

import android.graphics.Canvas;

import java.util.ArrayList;

import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 10/22/2016.
 */

public class NoUpdateBoard extends SolutionBoard {

    NoUpdateBoard(float xOffset, float yOffset, float scale, ArrayList<BasePiece> solution, int boardDimension) {
        super(xOffset, yOffset, scale, solution, boardDimension, null);
    }

    public void updateBoard() {};
}
