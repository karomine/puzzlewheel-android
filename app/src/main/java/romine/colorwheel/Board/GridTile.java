package romine.colorwheel.Board;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.PathShape;
import android.util.Log;

import romine.colorwheel.Pieces.AtomicGamePiece;
import romine.colorwheel.Pieces.BasePiece;
import romine.colorwheel.Shapes.Lines;
import romine.colorwheel.Shapes.TileSectionBoundary;
import romine.colorwheel.Shapes.TileSectionPaths;

/**
 * Created by karom on 10/18/2016.
 */

public abstract class GridTile {

    Canvas backgroundCanvas;
    private int xOffset;
    private int yOffset;
    float scale;
    TileColorPalette colorScheme;
    PaintComparator comp;
    private int[] blackBoundaries = new int[51];
    private PathShape[] fillShapes;

    GridTile(Canvas backgroundCanvas, float boardXOffset, float boardYOffset, int xPosition, int yPosition, float scale) {
        this.xOffset = (int) (boardXOffset + xPosition * scale);
        this.yOffset = (int) (boardYOffset + yPosition * scale);
        this.backgroundCanvas = backgroundCanvas;
        fillShapes = TileSectionPaths.makePathShapes(xOffset, yOffset, scale);
    }

    public boolean pieceOverlap() {
        return this.colorScheme.pieceOverlap();
    }

    public void display() {
        this.drawFill();
        this.drawBoundaries();
        this.drawBorder();
    }

    public void drawFill() {
        for (int i = 0; i < fillShapes.length; i++) {
            fillShapes[i].draw(this.backgroundCanvas, this.colorScheme.getPaint(i));
        }
    }

    public void drawBoundaries() {
        Paint p1, p2;
        int index = 0;
        for (int i = 0; i < 51; i++) {
            p1 = this.colorScheme.getPaint(TileSectionBoundary.getFirstBorderSection(i));
            p2 = this.colorScheme.getPaint(TileSectionBoundary.getSecondBorderSection(i));
            if (comp.comparePaints(p1, p2)) {
                this.drawBoundary(i, p1, 2);
            } else {
                blackBoundaries[index++] = i;
            }
        }
        for (int i = 0; i < index; i++) {
            this.drawBoundary(blackBoundaries[i], PaintColors.BLACK_PAINT, 2);
        }
    }

    public void drawBorder() {
        Lines.drawLine(xOffset, yOffset, xOffset + scale, yOffset, 2, PaintColors.BLACK_PAINT, backgroundCanvas);
        Lines.drawLine(xOffset + scale, yOffset, xOffset + scale, yOffset + scale, 2, PaintColors.BLACK_PAINT, backgroundCanvas);
        Lines.drawLine(xOffset + scale, yOffset + scale, xOffset, yOffset + scale, 2, PaintColors.BLACK_PAINT, backgroundCanvas);
        Lines.drawLine(xOffset, yOffset + scale, xOffset, yOffset, 2, PaintColors.BLACK_PAINT, backgroundCanvas);
    }

    public void drawBoundary(int path, Paint paint, float width) {
        TileSectionBoundary.drawBoundaryLine(path, this.xOffset, this.yOffset, this.scale, width, paint, this.backgroundCanvas);
    }

    public void addPieceInRanges(int[][] ranges, AtomicGamePiece atomicPiece, BasePiece parentPiece) {
        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                this.colorScheme.addPieces(atomicPiece, parentPiece, i);
            }
        }
    }

    public void removePieceInRanges(int[][] ranges, AtomicGamePiece atomicPiece, BasePiece parentPiece) {
        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                this.colorScheme.removePieces(atomicPiece, parentPiece, i);
            }
        }
    }

    public boolean match(GridTile other) {
        Paint p1;
        Paint p2;
        for (int i = 0; i < this.colorScheme.size(); i++) {
            p1 = this.colorScheme.getPaint(i);
            p2 = other.colorScheme.getPaint(i);
            if (p1 != p2 && !(p1 == PaintColors.WHITE_PAINT && p2 == PaintColors.CLEAR_PAINT ||
                    p1 == PaintColors.CLEAR_PAINT && p2 == PaintColors.WHITE_PAINT)) {
                return false;
            }
        }
        return true;
    }

    public boolean matchInRanges(int[][] ranges, GridTile other) {
        Paint p1, p2;
        for (int[] range : ranges) {
            for (int section : range) {
                p1 = colorScheme.getPaint(section);
                p2 = other.colorScheme.getPaint(section);
                if (p1 != p2 && !(p1 == PaintColors.CLEAR_PAINT && p2 == PaintColors.WHITE_PAINT)
                        && !(p1 == PaintColors.WHITE_PAINT && p2 == PaintColors.CLEAR_PAINT)) {
                    return false;
                }
            }
        }
        return true;
    }

    public BasePiece topPiece(float xOffset, float yOffset) {
        return this.colorScheme.topPiece(Lines.getSection(xOffset, yOffset, this.scale));
    }

    public void setColorsInRanges(int[][] ranges) {
        this.colorScheme.setColorCountsInRanges(ranges);
    }

    public void setColorsInRanges(int[][] ranges, AtomicGamePiece ignoredPiece) {
        this.colorScheme.setColorCountsInRanges(ranges, ignoredPiece);
    }
}
