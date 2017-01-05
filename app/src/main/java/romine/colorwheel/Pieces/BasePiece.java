package romine.colorwheel.Pieces;

import android.graphics.Paint;
import android.graphics.drawable.shapes.PathShape;

import java.util.ArrayList;
import java.util.Random;

import romine.colorwheel.Board.Board;
import romine.colorwheel.Board.GridTile;
import romine.colorwheel.Board.PaintColors;

/**
 * Created by karom on 10/18/2016.
 */

public abstract class BasePiece extends GamePiece {

    ArrayList<GamePiece> pieces;
    int id = -1;
    PieceColor color;
    static private Random rand = new Random();

    public BasePiece(ArrayList<GamePiece> pieces, PieceColor color) {
        super(getXDimension(pieces), getYDimension(pieces));
        this.pieces = pieces;
        this.color = color;
    }

    public Paint getColor() {
        return PaintColors.getPaint(color);
    }

    public BasePiece reorient(BasePiece other) {
        if (direction != other.direction) {
            flipVertically();
        }
        while (corner != other.corner) {
            rotateRight();
        }
        return other;
    }

    public abstract PathShape getMovingShape(float xOffset, float yOffset, float scale);

    BasePiece(ArrayList<GamePiece> pieces, Orientation direction, CornerLocation corner, Offset offset) {
        super(getXDimension(pieces), getYDimension(pieces), direction, corner, offset);
    }

    public abstract GamePiece clonePiece();

    public void jumblePiece() {
        double chance = rand.nextInt() % 4;
        for (int i = 0; i < chance; i++) {
            this.rotateLeft();
        }
        if (rand.nextBoolean()) {
            this.flipVertically();
        }
    }

    public void updateBoard(Board board, int xOffset, int yOffset) {
        for(GamePiece piece : this.pieces) {
            piece.updateBoard(board, xOffset + piece.getXOffset(), yOffset + piece.getYDimension());
        }
    }

    public ArrayList<GamePiece> getPieces() {
        return this.pieces;
    }

    ArrayList<GamePiece> copyPieces() {
        ArrayList<GamePiece> copy = new ArrayList<GamePiece>();
        for (GamePiece piece : this.pieces) {
            copy.add(piece.clonePiece());
        }
        return copy;
    }

    public void addPiece(GridTile[][] tiles, int xOffset, int yOffset) {
        addPiece(tiles, xOffset, yOffset, this);
    }

    void addPiece(GridTile[][] tiles, int xOffset, int yOffset, BasePiece parentPiece) {
        this.setOffsets(xOffset, yOffset);
        for (GamePiece piece : this.pieces) {
            piece.addPiece(tiles, xOffset + piece.getXOffset(), yOffset + piece.getYOffset(), parentPiece);
        }
    }

    public void removePiece(GridTile[][] tiles) {
        this.removePiece(tiles, this.getXOffset(), this.getYOffset(), this);
    }

    void removePiece(GridTile[][] tiles, int xOffset, int yOffset, BasePiece parentPiece) {
        for (GamePiece piece : this.pieces) {
            piece.removePiece(tiles, xOffset + piece.getXOffset(), yOffset + piece.getYOffset(), parentPiece);
        }
    }

    public int getDimension() {
        return Math.max(this.xDimension, this.yDimension) + 1;
    }

    public void rotateRight() {
        super.rotateRight();
        for (GamePiece piece : this.pieces) {
            piece.rotateOffsetRight(this.yDimension);
            piece.rotateRight();
        }
        this.swapDimension();
    }

    public void rotateLeft() {
        super.rotateLeft();
        for (GamePiece piece : this.pieces) {
            piece.rotateOffsetLeft(this.xDimension);
            piece.rotateLeft();
        }
        this.swapDimension();
    }

    public void flipVertically() {
        super.flipVertically();
        for (GamePiece piece : this.pieces) {
            piece.flipOffsetsVertically(this.yDimension);
            piece.flipVertically();
        }
    }

    public void flipHorizontally() {
        super.flipHorizontally();
        for (GamePiece piece : this.pieces) {
            piece.flipOffsetsHorizontally(this.xDimension);
            piece.flipHorizontally();
        }
    }

    public boolean isClear() {
        return color == GamePiece.PieceColor.CLEAR;
    }

    public void setOffsets(int xOffset, int yOffset) {
        this.setXOffset(xOffset);
        this.setYOffset(yOffset);
    }

    private void swapDimension() {
        int temp = this.xDimension;
        this.xDimension = this.yDimension;
        this.yDimension = temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasId() {
        return this.id != -1;
    }

    private static int getXDimension(ArrayList<GamePiece> pieces) {
        int max = 0;
        for (GamePiece piece : pieces) {
            max = Math.max(max, piece.getXOffset() + piece.getXDimension());
        }
        return max;
    }

    private static int getYDimension(ArrayList<GamePiece> pieces) {
        int max = 0;
        for (GamePiece piece : pieces) {
            max = Math.max(max, piece.getYOffset() + piece.getYDimension());
        }
        return max;
    }
}
