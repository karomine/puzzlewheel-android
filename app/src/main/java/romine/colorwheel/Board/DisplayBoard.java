package romine.colorwheel.Board;

import android.graphics.Canvas;

import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 10/22/2016.
 */

public class DisplayBoard extends SingleBoard {

    private BasePiece displayPiece = null;

    public DisplayBoard(float xOffset, float yOffset, float scale, int boardDimension, Canvas canvas) {
        super(xOffset, yOffset, scale, boardDimension, canvas);
        this.boardGrid = new DisplayClearTile[boardDimension][boardDimension];
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                boardGrid[i][j] = new DisplayClearTile(canvas, xOffset, yOffset, i, j, scale);
            }
        }
    }

    public void addPiece(BasePiece piece, int xOffset, int yOffset) {
        super.addPiece(piece, xOffset, yOffset);
        this.displayPiece = piece;
    }

    public void removePiece(BasePiece piece, int xOffset, int yOffset) {
        super.removePiece(piece, xOffset, yOffset);
        this.displayPiece = null;
    }

    public boolean hasDisplayPiece() {
        return displayPiece != null;
    }

    public void removeDisplayPiece() {
        removePiece(displayPiece, displayPiece.getXOffset(), displayPiece.getYOffset());
    }

    public void addDisplayPiece() {
        addPiece(displayPiece, displayPiece.getXOffset(), displayPiece.getYOffset());
    }

    public void rotateDisplayPieceRight() {
        if (displayPiece != null) {
            BasePiece piece = displayPiece;
            int xOffset = boardDimension - (piece.getYDimension() + piece.getYOffset() + 1);
            int yOffset = piece.getXOffset();
            removePiece(piece, piece.getXOffset(), piece.getYOffset());
            piece.rotateRight();
            if (piece.getXDimension() == piece.getYDimension()) {
                addPiece(piece, 0, 0);
            } else {
                addPiece(piece, xOffset, yOffset);
            }
        }
    }

    public void rotateDisplayPieceLeft() {
        if (displayPiece != null) {
            BasePiece piece = displayPiece;
            int xOffset = piece.getYOffset();
            int yOffset = boardDimension - (piece.getXDimension() + piece.getXOffset() + 1);
            removePiece(piece, piece.getXOffset(), piece.getYOffset());
            piece.rotateLeft();
            if (piece.getXDimension() == piece.getYDimension()) {
                addPiece(piece, 0, 0);
            } else {
                addPiece(piece, xOffset, yOffset);
            }
        }
    }

    public void flipDisplayPieceVertically() {
        if (displayPiece != null) {
            BasePiece piece = displayPiece;
            int xOffset = piece.getXOffset();
            int yOffset = boardDimension - (piece.getYDimension() + piece.getYOffset() + 1);
            removePiece(piece, piece.getXOffset(), piece.getYOffset());
            piece.flipVertically();
            if (piece.getXDimension() == piece.getYDimension()) {
                addPiece(piece, 0, 0);
            } else {
                addPiece(piece, xOffset, yOffset);
            }
        }
    }

    public void flipDisplayPieceHorizontally() {
        if (displayPiece != null) {
            BasePiece piece = displayPiece;
            int xOffset = boardDimension - (piece.getXDimension() + piece.getXOffset() + 1);
            int yOffset = piece.getYOffset();
            removePiece(piece, piece.getXOffset(), piece.getYOffset());
            piece.flipHorizontally();
            if (piece.getXDimension() == piece.getYDimension()) {
                addPiece(piece, 0, 0);
            } else {
                addPiece(piece, xOffset, yOffset);
            }
        }
    }
}
