package romine.colorwheel.Board;

import android.graphics.Paint;

import java.util.ArrayList;

import romine.colorwheel.Pieces.AtomicGamePiece;
import romine.colorwheel.Pieces.BasePiece;
import romine.colorwheel.Pieces.GamePiece;

/**
 * Created by karom on 10/18/2016.
 */

public class ColorPalette {

    private ArrayList<AtomicGamePiece> atomicPieces = new ArrayList();
    private ArrayList<BasePiece> basePieces = new ArrayList();
    private Paint paint = PaintColors.WHITE_PAINT;
    private Boolean clear = false;
    private int redCount = 0;
    private int blueCount = 0;
    private int yellowCount = 0;

    ColorPalette() {}

    public Paint getPaint() {
        return this.paint;
    }

    public BasePiece topPiece() {
        return this.basePieces.size() > 0 ? this.basePieces.get(this.basePieces.size() - 1) : null;
    }

    public int numPieces() {
        return atomicPieces.size();
    }

    public void addBasePiece(BasePiece piece) {
        this.basePieces.add(piece);
    }

    public void removeBasePiece(BasePiece piece) {
        this.basePieces.remove(piece);
    }

    public void addAtomicPiece(AtomicGamePiece piece) {
        this.atomicPieces.add(piece);
        this.addColor(piece.getColor());
        this.setPaint();
    }

    public void removeAtomicPiece(AtomicGamePiece piece) {
        this.atomicPieces.remove(piece);
        this.removeColor(piece.getColor());
        this.setPaint();
    }

    public void setColorCounts() {
        this.clearPalette();
        for (AtomicGamePiece piece : atomicPieces) {
            this.addColor(piece.getColor());
        }
        this.setPaint();
    }

    public void setColorCounts(AtomicGamePiece ignoredPiece) {
        this.clearPalette();
        for (AtomicGamePiece piece : atomicPieces) {
            if (piece != ignoredPiece) {
                this.addColor(piece.getColor());
            }
        }
        this.setPaint();
    }

    private void setPaint() {
        this.paint = PaintColors.getPaint(this.redCount, this.yellowCount, this.blueCount, clear);
    }

    private void clearPalette() {
        this.redCount = 0;
        this.yellowCount = 0;
        this.blueCount = 0;
    }

    private void addColor(GamePiece.PieceColor color) {
        switch (color) {
            case RED:
                clear = false;
                this.redCount++;
                break;
            case BLUE:
                clear = false;
                this.blueCount++;
                break;
            case YELLOW:
                clear = false;
                this.yellowCount++;
                break;
            case CLEAR:
                clear = true;
                this.clearPalette();
                break;
        }
    }

    private void removeColor(GamePiece.PieceColor color) {
        switch (color) {
            case RED:
                this.redCount--;
                break;
            case BLUE:
                this.blueCount--;
                break;
            case YELLOW:
                this.yellowCount--;
                break;
            case CLEAR:
                clear = false;
                this.setColorCounts();
                break;
        }
        this.setColorCounts();
    }
}
