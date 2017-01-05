package romine.colorwheel.Shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by karom on 10/18/2016.
 */

public class Lines {

    private final static float[][] lines = {
            {2, 0}, {1, 0}, {.5f, 0}, {-2, 1}, {2, -1},
            {-.5f, .5f}, {-1, 1},{-2, 2}, {-.5f, 1}, {.5f, .5f}
    };

    private final static int[][][] sections = {
            {{4, 5},{}},
            {{2, 5},{3, 4}},
            {{2, 3},{}},
            {{1, 3},{2}},
            {{0, 5},{1}},
            {{5},{0}},
            {{9, 3},{0, 5}},
            {{3},{9}},
            {{6},{3, 9}},
            {{8},{0, 6}},
            {{},{8, 0}},
            {{0, 7},{8, 9}},
            {{},{9, 7}},
            {{9},{1, 7}},
            {{1},{8, 4}},
            {{4},{8}},
            {{8, 4},{2, 7}},
            {{2},{7}},
            {{2, 7},{6}},
            {{4, 6},{5}},
            {{2},{4, 5}},
            {{1, 6},{5, 2}},
            {{5},{2, 3}},
            {{0, 3},{5}},
            {{0, 6},{1, 3}},
            {{9},{0, 3}},
            {{0, 8},{9}},
            {{8, 9},{1, 6}},
            {{7, 9},{8}},
            {{8},{7, 4}},
            {{1, 7},{4, 6}},
            {{4, 7},{2}}
    };

    static public void drawLine(float xStart, float yStart, float xEnd, float yEnd,
                                float width, Paint paint, Canvas canvas) {
        paint.setStrokeWidth(width);
        canvas.drawLine(xStart, yStart, xEnd, yEnd, paint);
    }

    static public int getSection(float xCoordinate, float yCoordinate, float scale) {
        for (int i = 0; i < sections.length; i++) {
            if (fallAbove(getAboveLines(i), xCoordinate, yCoordinate, scale) &&
            fallBelow(getBelowLines(i), xCoordinate, yCoordinate, scale)) {
                return i;
            }
        }
        return -1;
    }

    static private Boolean fallAbove(int[] lines, float xCoordinate, float yCoordinate, float scale) {
        for (int i = 0; i < lines.length; i++) {
            if (!above(xCoordinate, yCoordinate, lines[i], scale)) {
                return false;
            }
        }
        return true;
    }

    static private Boolean fallBelow(int[] lines, float xCoordinate, float yCoordinate, float scale) {
        for (int i = 0; i < lines.length; i++) {
            if (above(xCoordinate, yCoordinate, lines[i], scale)) {
                return false;
            }
        }
        return true;
    }

    static private int[] getBelowLines(int index) {
        return sections[index][1];
    }

    static private int[] getAboveLines(int index) {
        return sections[index][0];
    }

    static private float getSlope(int index) {
        return lines[index][0];
    }

    static private float getYIntercept(int index, float scale) {
        return lines[index][1] * scale;
    }

    static private Boolean above(float xCoordinate, float yCoordinate, int index, float scale) {
        return yCoordinate <= xCoordinate * getSlope(index) + getYIntercept(index, scale);
    }
}
