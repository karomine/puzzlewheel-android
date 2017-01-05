package romine.colorwheel.Board;

import android.graphics.Canvas;

import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 10/22/2016.
 */

public class DisplayBoard extends SingleBoard {

    private BasePiece displayPiece = null;

    DisplayBoard(float xOffset, float yOffste, float scale, int boardDimension, Canvas canvas) {
        super(xOffset, yOffste, scale, boardDimension, canvas);
        this.boardGrid = new DisplayClearTile[boardDimension][boardDimension];
    }

    public void addPiece(BasePiece piece, int xOffset, int yOffset) {
        super.addPiece(piece, xOffset, yOffset);
        this.displayPiece = piece;
    }

    public void removePiece(BasePiece piece, int xOffset, int yOffset) {
        super.removePiece(piece, xOffset, yOffset);
        this.displayPiece = null;
    }

    private void returnDisplayPiece(int xOffset, int yOffset) {
        if (displayPiece.getXDimension() == displayPiece.getYDimension() && displayPiece.getXDimension() <= 1) {
            addPiece(displayPiece, 0, 0);
        } else {
            addPiece(displayPiece, xOffset, yOffset);
        }
    }

    public void rotateDisplayPieceRight() {
        if (displayPiece != null) {
            int x = boardDimension - (displayPiece.getYDimension() + displayPiece.getYOffset() + 1);
            int y = displayPiece.getXOffset();
            this.removePiece(displayPiece, displayPiece.getXOffset(), displayPiece.getYOffset());
            displayPiece.rotateRight();
            returnDisplayPiece(x, y);
        }
    }

    public void rotateDisplayPieceLeft() {
        if (displayPiece != null) {
            int x = displayPiece.getXOffset();
            int y = boardDimension - (displayPiece.getXDimension() + displayPiece.getXOffset() + 1);
            this.removePiece(displayPiece, displayPiece.getXOffset(), displayPiece.getYOffset());
            displayPiece.rotateLeft();
            returnDisplayPiece(x, y);
        }
    }

    public void flipDisplayPieceVertically() {
        if (displayPiece != null) {
            int x = displayPiece.getXOffset();
            int y = boardDimension - (displayPiece.getYDimension() + displayPiece.getYOffset() + 1);
            this.removePiece(displayPiece, displayPiece.getXOffset(), displayPiece.getYOffset());
            displayPiece.flipVertically();
            returnDisplayPiece(x, y);
        }
    }

    public void flipDisplayPieceHorizontally() {
        if (displayPiece != null) {
            int x = boardDimension - (displayPiece.getXDimension() + displayPiece.getXOffset() + 1);
            int y = displayPiece.getYDimension();
            this.removePiece(displayPiece, displayPiece.getXOffset(), displayPiece.getYOffset());
            displayPiece.flipHorizontally();
            returnDisplayPiece(x, y);
        }
    }
}
