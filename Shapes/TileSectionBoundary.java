package romine.colorwheel.Shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by karom on 10/19/2016.
 */

public class TileSectionBoundary {

    private static final int[][] boundaryLines = {
            {2,9},{2,17},{2,10},{4,11},{4,19},{12,4},{2,9},{2,17},
            {2,10},{6,13},{21,6},{6,14},{0,15},{0,23},{0,8},{1,8},
            {1,9},{8,16},{9,16},{3,10},{3,11},{11,18},{10,18},{5,13},
            {5,12},{12,20},{13,20},{7,14},{14,22},{22,15},{15,7},{8,23},
            {15,23},{21,14},{21,13},{12,19},{19,11},{17,9},{17,10},{23,16},
            {16,17},{17,18},{18,19},{19,20},{20,21},{21,22},{22,23},{24,23},
            {24,21},{24,19},{24,17}
    };

    private static final int[][] borderingSections = {
            {0,19},{19,18},{17,18},{15,14},{14,13},{12,13},{0,19},{19,18},
            {17,18},{9,10},{9,8},{7,8},{5,4},{4,3},{3,2},{2,1},
            {1,0},{22,1},{20,1},{17,16},{15,16},{16,29},{16,31},{11,10},
            {11,12},{11,28},{11,26},{6,7},{6,25},{6,23},{6,5},{22,3},
            {23,4},{8,25},{9,26},{28,13},{29,14},{19,20},{18,31},{22,21},
            {21,20},{30,31},{29,30},{27,28},{26,27},{24,25},{23,24},{21,24},
            {24,27},{27,30},{30,21}
    };

    public static void drawBoundaryLine(int lineNum, int xOffset, int yOffset,
                                        float scale, float width, Paint paint, Canvas canvas) {
        Lines.drawLine(Points.getXPoint(boundaryLines[lineNum][0], xOffset, scale),
                Points.getYPoint(boundaryLines[lineNum][0], yOffset, scale),
                Points.getXPoint(boundaryLines[lineNum][1], xOffset, scale),
                Points.getYPoint(boundaryLines[lineNum][1], yOffset, scale),
                width, paint, canvas);
    }

    public static int[] getPath(int index) {
        return boundaryLines[index];
    }

    public static int getFirstBorderSection(int index) {
        return borderingSections[index][0];
    }

    public static int getSecondBorderSection(int index) {
        return borderingSections[index][1];
    }
}
