package romine.colorwheel.Board;

import android.graphics.Canvas;

import java.util.ArrayList;

import romine.colorwheel.Pieces.BasePiece;


/**
 * Created by karom on 10/22/2016.
 */

public abstract class SingleBoard extends Board {

    GridTile[][] boardGrid;
    private Canvas backgroundCanvas;

    SingleBoard(float xOffset, float yOffset, float scale, int boardDimension, Canvas canvas) {
        super(xOffset, yOffset, scale, canvas);
        this.boardDimension = boardDimension;
        board = this;
        backgroundCanvas = canvas;
        piecesOnBoard = new ArrayList<>();
    }

    public boolean pieceOverlap(int xOffset, int yOffset, int xDimension, int yDimension) {
        for (int i = xOffset; i < xOffset + xDimension; i++) {
            for (int j = yOffset; j < yOffset + yDimension; j++) {
                if (boardGrid[i][j].pieceOverlap()) {
                    return true;
                }
            }
        }
        return false;
    }

    int getBoardDimension() {
        return boardDimension;
    }
    ArrayList<BasePiece> getPiecesOnBoard() {
        return piecesOnBoard;
    }
    public GridTile[][] getBoardGrid() {
        return boardGrid;
    }
    Board getBoard() {
        return this;
    }
    public float getXOffset() {
        return xOffset;
    }
    public float getYOffset() {
        return yOffset;
    }
    public float getScale() {
        return scale;
    }
}
