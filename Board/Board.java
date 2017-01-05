package romine.colorwheel.Board;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

import romine.colorwheel.Pieces.BasePiece;
import romine.colorwheel.Pieces.GamePiece;
import romine.colorwheel.Shapes.Lines;

/**
 * Created by karom on 10/21/2016.
 */

public abstract class Board {

    private Canvas backgroundCanvas;
    float xOffset;
    float yOffset;
    float scale;
    int boardDimension;
    ArrayList<BasePiece> piecesOnBoard;
    Board board;


    Board(float xOffset, float yOffset, float scale, Canvas backgroundCanvas) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.scale = scale;
        this.backgroundCanvas = backgroundCanvas;
    }

    abstract float getXOffset();
    abstract float getYOffset();
    abstract float getScale();
    abstract int getBoardDimension();
    abstract ArrayList<BasePiece> getPiecesOnBoard();
    public abstract GridTile[][] getBoardGrid();
    abstract Board getBoard();

    boolean onBoard(int x, int y) {
        return x >= getXOffset() && x <= getXOffset() + getScale() * getBoardDimension()
                && y >= getYOffset() && y <= getYOffset() + getScale() * getBoardDimension();
    }

    public void addPiece(BasePiece piece, int x, int y) {
        getPiecesOnBoard().add(piece);
        piece.addPiece(getBoardGrid(), x, y);
        getBoard().updateBoard(piece, x, y);
    }

    public void removePiece(BasePiece piece, int x, int y) {
        getPiecesOnBoard().remove(piece);
        piece.removePiece(getBoardGrid());
        getBoard().updateBoard(piece, x, y);
    }

    public void updateBoard(GamePiece parentPiece, int x , int y) {
        for (GamePiece piece: parentPiece.getPieces()) {
            piece.updateBoard(this, x + piece.getXOffset(), y + piece.getYOffset());
        }
    }

    public void display() {
        GridTile[][] board = getBoardGrid();

        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                board[i][j].display();
            }
        }

        board[0][0].display();
        board[1][1].display();
        board[2][2].display();

        this.drawBorder();
    }

    public void drawBorder() {
        Lines.drawLine(getXOffset(),
                getYOffset(),
                getXOffset(),
                getYOffset() + getBoardDimension() * getScale(),
                3, PaintColors.BLACK_PAINT, backgroundCanvas);
        Lines.drawLine(getXOffset(),
                getYOffset() + getBoardDimension() * getScale(),
                getXOffset() + getBoardDimension() * getScale(),
                getYOffset() + getBoardDimension() * getScale(),
                3, PaintColors.BLACK_PAINT, backgroundCanvas);
        Lines.drawLine(getXOffset() + getBoardDimension() * getScale(),
                getYOffset() + getBoardDimension() * getScale(),
                getXOffset() + getBoardDimension() * getScale(),
                getYOffset(),
                3, PaintColors.BLACK_PAINT, backgroundCanvas);
        Lines.drawLine(getXOffset() + getBoardDimension() * getScale(),
                getYOffset(),
                getXOffset(),
                getYOffset(),
                3, PaintColors.BLACK_PAINT, backgroundCanvas);

    }

    public boolean pieceInRange(BasePiece piece, int x, int y) {
        int xOffset, yOffset;
        for (GamePiece pieces : piece.getPieces()) {
            xOffset = x + piece.getXOffset() + piece.getXDimension();
            yOffset = y + piece.getYOffset() + piece.getYDimension();
            if (xOffset < 0 || xOffset >= getBoardDimension() || yOffset < 0 || yOffset >= getBoardDimension()) {
                return false;
            }
        }
        return true;
    }

    public BasePiece topPiece(int x, int y, int xOff, int yOff) {
        return getBoardGrid()[x][y].topPiece(xOff, yOff);
    }
}
