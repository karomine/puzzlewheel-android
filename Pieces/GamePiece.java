package romine.colorwheel.Pieces;


import java.lang.reflect.Constructor;
import java.util.ArrayList;

import romine.colorwheel.Board.Board;
import romine.colorwheel.Board.GridTile;

import static romine.colorwheel.Pieces.GamePiece.CornerLocation.*;
import static romine.colorwheel.Pieces.GamePiece.Orientation.*;

/**
 * Created by karom on 10/18/2016.
 */

public abstract class GamePiece {

    enum CornerLocation {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    enum Orientation {
        INNER, OUTER
    }

    public enum PieceColor {
        RED, BLUE, YELLOW, CLEAR, NAN
    }

    CornerLocation corner = TOP_LEFT;
    Orientation direction = INNER;
    Offset offset;
    int xDimension;
    int yDimension;
    Constructor<GamePiece> constructor;

    GamePiece() {
        this.xDimension = 0;
        this.yDimension = 0;
        this.offset = new Offset(0, 0);
    }

    GamePiece(Offset offset) {
        this.xDimension = 0;
        this.yDimension = 0;
        this.offset = offset;
    }

    GamePiece(int xDimension, int yDimension) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.offset = new Offset(0, 0);
    }

    GamePiece(int xDimension, int yDimension, Offset offset) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.offset = offset;
    }

    GamePiece(int xDimension, int yDimension, Orientation direction, CornerLocation corner, Offset offset) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.direction = direction;
        this.corner = corner;
        this.offset = offset;
    }

    abstract void removePiece(GridTile[][] tiles, int xOffset, int yOffset, BasePiece parentPiece);
    abstract void addPiece(GridTile[][] tiles, int xOffset, int yOffset, BasePiece parentPiece);
    abstract boolean hasColor(PieceColor color);
    abstract GamePiece clonePiece();
    public abstract void updateBoard(Board board, int xOffset, int yOffset);
    public abstract ArrayList<GamePiece> getPieces();

    void rotateOffsetRight(int parentPieceYDimension) {
        offset.rotateRight(parentPieceYDimension, this.yDimension);
    }

    void rotateOffsetLeft(int parentPieceXDimension) {
        offset.rotateLeft(parentPieceXDimension, this.xDimension);
    }

    void flipOffsetsVertically(int parentPieceYDimension) {
        this.offset.flipVertically(parentPieceYDimension, this.yDimension);
    }

    void flipOffsetsHorizontally(int parentPieceXDimension) {
        this.offset.flipHorizontally(parentPieceXDimension, this.xDimension);
    }

    private void swapOrientation() {
        this.direction = this.direction == INNER ? OUTER : INNER;
    }

    public void flipVertically() {
        switch (this.corner) {
            case TOP_RIGHT:
            case BOTTOM_LEFT:
                this.rotateRight();
                break;
            case TOP_LEFT:
            case BOTTOM_RIGHT:
                this.rotateLeft();
                break;
        }
        this.swapOrientation();
    }

    public void flipHorizontally() {
        switch (this.corner) {
            case TOP_RIGHT:
            case BOTTOM_LEFT:
                this.rotateLeft();
                break;
            case TOP_LEFT:
            case BOTTOM_RIGHT:
                this.rotateRight();
                break;
        }
        this.swapOrientation();
    }

    public void rotateRight() {
        switch (this.corner) {
            case TOP_RIGHT:
                this.corner = BOTTOM_RIGHT;
                break;
            case TOP_LEFT:
                this.corner = TOP_RIGHT;
                break;
            case BOTTOM_LEFT:
                this.corner = TOP_LEFT;
                break;
            case BOTTOM_RIGHT:
                this.corner = BOTTOM_LEFT;
                break;
        }
    }

    public void rotateLeft() {
        switch (this.corner) {
            case TOP_RIGHT:
                this.corner = TOP_LEFT;
                break;
            case TOP_LEFT:
                this.corner = BOTTOM_LEFT;
                break;
            case BOTTOM_LEFT:
                this.corner = BOTTOM_RIGHT;
                break;
            case BOTTOM_RIGHT:
                this.corner = TOP_RIGHT;
                break;
        }
    }

    public int getXDimension() {
        return xDimension;
    }

    public int getYDimension() {
        return yDimension;
    }

    public int getXOffset() {
        return this.offset.getXOffset();
    }

    public int getYOffset() {
        return this.offset.getYOffset();
    }

    public void setXOffset(int offset) {
        this.offset.setXOffset(offset);
    }

    public void setYOffset(int offset) {
        this.offset.setYOffset(offset);
    }

    public void setOffsets(Offset offset) {
        this.offset = offset;
    }


}


