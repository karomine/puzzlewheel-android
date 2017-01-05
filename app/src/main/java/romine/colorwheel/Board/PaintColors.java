package romine.colorwheel.Board;

import android.graphics.Color;
import android.graphics.Paint;

import romine.colorwheel.Pieces.GamePiece;

/**
 * Created by karom on 10/19/2016.
 */

public class PaintColors {

    public final static Paint RED_PAINT = makePaint(255, 0, 0);
    public final static Paint BLUE_PAINT = makePaint(0, 0, 255);
    public final static Paint YELLOW_PAINT = makePaint(255, 255, 0);
    public final static Paint PURPLE_PAINT = makePaint(128, 0, 128);
    public final static Paint ORANGE_PAINT = makePaint(255, 165, 0);
    public final static Paint GREEN_PAINT = makePaint(0, 128, 0);
    public final static Paint WHITE_PAINT = makePaint(255, 255, 255);
    public final static Paint CLEAR_PAINT = makePaint(255, 255, 254);
    public final static Paint CLEAR_DISPLAY_PAINT = makePaint(247, 240, 212);
    public final static Paint BLACK_PAINT = makePaint(0, 0, 0);

    private static Paint makePaint(int red, int green, int blue) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(red, green, blue));
        return paint;
    }

    public static Paint getPaint(int red, int yellow, int blue, Boolean clear) {
        if (clear) {
            return CLEAR_PAINT;
        } else if (blue == 0 && yellow == 0 && red == 0) {
            return WHITE_PAINT;
        } else if (blue == 0 && yellow == 0) {
            return RED_PAINT;
        } else if (yellow == 0 && red == 0) {
            return BLUE_PAINT;
        } else if (blue == 0 && red == 0) {
            return YELLOW_PAINT;
        } else if (blue == 0) {
            return ORANGE_PAINT;
        } else if (yellow == 0) {
            return PURPLE_PAINT;
        } else if (red == 0){
            return GREEN_PAINT;
        } else {
            return BLACK_PAINT;
        }
    }

    public static Paint getPaint(GamePiece.PieceColor color) {
        switch (color) {
            case RED:
                return RED_PAINT;
            case YELLOW:
                return YELLOW_PAINT;
            case BLUE:
                return BLUE_PAINT;
            default:
                return CLEAR_DISPLAY_PAINT;
        }
    }

}
