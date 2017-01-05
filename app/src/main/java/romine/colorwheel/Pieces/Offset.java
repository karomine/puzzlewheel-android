package romine.colorwheel.Pieces;

/**
 * Created by karom on 10/18/2016.
 */

public class Offset {

    private int xOffset;
    private int yOffset;

    public Offset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public void setOffsets(int xOffset, int yOffset) {
        setXOffset(xOffset);
        setYOffset(yOffset);
    }

    public void setXOffset(int offset) {
        this.xOffset = offset;
    }

    public void setYOffset(int offset) {
        this.yOffset = offset;
    }

    public void rotateRight(int basePieceYDimension, int offsetPieceYDimension) {
        setOffsets(basePieceYDimension - yOffset - offsetPieceYDimension, xOffset);
    }

    public void rotateLeft(int basePieceXDimension, int offsetPieceXDimension) {
        setOffsets(yOffset, basePieceXDimension - xOffset - offsetPieceXDimension);
    }

    public void flipVertically(int basePieceYDimension, int offsetPieceYDimension) {
        this.yOffset = basePieceYDimension - this.yOffset - offsetPieceYDimension;
    }

    public void flipHorizontally(int basePieceXDimension, int offsetPieceXDimension) {
        this.xOffset = basePieceXDimension - this.xOffset - offsetPieceXDimension;
    }

    public String toString() {
        return "(" + xOffset + ", " + yOffset + ")";
    }
}
