package romine.colorwheel.Board;

import android.graphics.Paint;

/**
 * Created by karom on 10/21/2016.
 */

public class BoardPaintComparator implements PaintComparator {

    BoardPaintComparator() {}

    public boolean comparePaints(Paint p1, Paint p2) {
        return p1 == p2 || p1 == PaintColors.CLEAR_PAINT && p2 == PaintColors.WHITE_PAINT
                || p1 == PaintColors.WHITE_PAINT && p2 == PaintColors.CLEAR_PAINT;
    }
}
