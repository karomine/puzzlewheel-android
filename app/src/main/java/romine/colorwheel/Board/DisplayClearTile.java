package romine.colorwheel.Board;

import android.graphics.Canvas;

/**
 * Created by karom on 10/21/2016.
 */

public class DisplayClearTile extends GridTile {

    static final DisplayClearPaintComparator paintComparator = new DisplayClearPaintComparator();

    DisplayClearTile(Canvas backgroundCanvas, float boardXOffset, float boardYOffset, int xPosition, int yPosition, float scale) {
        super(backgroundCanvas, boardXOffset, boardYOffset, xPosition, yPosition, scale);
        this.colorScheme = new DisplayClearTileColorPalette();
        this.comp = paintComparator;
        this.scale = scale;
    }

}
