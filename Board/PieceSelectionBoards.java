package romine.colorwheel.Board;

import android.graphics.Canvas;

import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 10/22/2016.
 */

public class PieceSelectionBoards extends MultiBoards {

    private DisplayBoard[] boards;
    private int boardIndex;

    PieceSelectionBoards(float xOffset, float yOffset, float scale, int boardDimension, Canvas canvas) {
        super(xOffset, yOffset, scale, canvas);
        boards = new DisplayBoard[boardDimension - 1];
        for (int i = 2; i <= boardDimension; i++) {
            boards[i-2] = new DisplayBoard(xOffset, yOffset, scale, boardDimension, canvas);
        }
        boardIndex = 2;
    }

    public void addPiece(BasePiece piece, int x, int y) {
        boardIndex = Math.max(piece.getDimension(), 2);
        addPiece(piece, x, y);
        display();
    }

    public void removePiece(BasePiece piece, int x, int y) {
        removePiece(piece, x, y);
        boardIndex = 2;
        display();
    }

    Board getBoard() {
        return boards[boardIndex-2];
    }
}
