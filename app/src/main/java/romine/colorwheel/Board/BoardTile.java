package romine.colorwheel.Board;

import android.graphics.Canvas;

/**
 * Created by karom on 10/21/2016.
 */

public class BoardTile extends GridTile {

    final static BoardPaintComparator paintComparator = new BoardPaintComparator();

    BoardTile(Canvas backgroundCanvas, float boardXOffset, float boardYOffset, int xPosition, int yPosition, float scale) {
        super(backgroundCanvas, boardXOffset, boardYOffset, xPosition, yPosition, scale);
        this.colorScheme = new TileColorPalette();
        this.comp = paintComparator;
        this.scale = scale;
    }

}
