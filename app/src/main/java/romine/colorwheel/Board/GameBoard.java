package romine.colorwheel.Board;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by karom on 10/22/2016.
 */

public class GameBoard extends SingleBoard {

    public GameBoard(float xOffset, float yOffset, float scale, int boardDimension, Canvas canvas) {
        super(xOffset, yOffset, scale, boardDimension, canvas);
        this.boardGrid = new BoardTile[boardDimension][boardDimension];
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                boardGrid[i][j] = new BoardTile(canvas, xOffset, yOffset, i, j, scale);
            }
        }
        piecesOnBoard = new ArrayList<>();
    }

}
