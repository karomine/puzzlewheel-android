package romine.colorwheel.Board;

import android.graphics.Paint;

/**
 * Created by karom on 10/21/2016.
 */

public class DisplayClearPaintComparator implements PaintComparator {

    DisplayClearPaintComparator() {}

    public boolean comparePaints(Paint p1, Paint p2) {
        return p1 == p2;
    }

}
