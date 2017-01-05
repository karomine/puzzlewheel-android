package romine.colorwheel.Board;

import android.graphics.Paint;

import romine.colorwheel.Board.PaintColors;
import romine.colorwheel.Board.TileColorPalette;


/**
 * Created by karom on 10/18/2016.
 */

public class DisplayClearTileColorPalette extends TileColorPalette {

    public Paint getPaint(int index) {
        Paint paint = super.getPaint(index);
        if (paint == PaintColors.CLEAR_PAINT) {
            return PaintColors.CLEAR_DISPLAY_PAINT;
        } else {
            return paint;
        }
    }
}
