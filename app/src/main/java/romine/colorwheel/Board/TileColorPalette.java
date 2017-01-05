package romine.colorwheel.Board;

import android.graphics.Paint;
import android.util.Log;

import romine.colorwheel.Board.ColorPalette;
import romine.colorwheel.Pieces.AtomicGamePiece;
import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 10/18/2016.
 */

public class TileColorPalette {

    ColorPalette[] colorPalettes = new ColorPalette[32];

    TileColorPalette() {
        for (int i = 0; i < 32; i++) {
            colorPalettes[i] = new ColorPalette();
        }
    }

    public void addPieces(AtomicGamePiece atomicPiece, BasePiece basePiece, int index) {
        this.colorPalettes[index].addAtomicPiece(atomicPiece);
        this.colorPalettes[index].addBasePiece(basePiece);
    }

    public void removePieces(AtomicGamePiece atomicPiece, BasePiece basePiece, int index) {
        this.colorPalettes[index].removeAtomicPiece(atomicPiece);
        this.colorPalettes[index].removeBasePiece(basePiece);
    }

    public boolean pieceOverlap() {
        for (int i = 0; i < colorPalettes.length; i++) {
            if (colorPalettes[i].numPieces() > 1) {
                return true;
            }
        }
        return false;
    }

    public BasePiece topPiece(int index) {
        return this.colorPalettes[index].topPiece();
    }

    public Paint getPaint(int index) {
        return this.colorPalettes[index].getPaint();
    }

    public void setColorCountsInRanges(int[][] ranges, AtomicGamePiece ignoredPiece) {
        for (int[] range : ranges) {
            for (int index : range) {
                this.colorPalettes[index].setColorCounts(ignoredPiece);
            }
        }
    }

    public void setColorCountsInRanges(int[][] ranges) {
        for (int[] range : ranges) {
            for (int index : range) {
                this.colorPalettes[index].setColorCounts();
            }
        }
    }

    public int size() {
        return this.colorPalettes.length;
    }
}
