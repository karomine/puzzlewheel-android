package romine.colorwheel.Shapes;

import android.graphics.Path;

/**
 * Created by karom on 10/19/2016.
 */

public class TileSectionPaths {

    private final static int[][] paths = {
            {1, 2, 9},
            {1, 9, 16, 8},
            {1, 8, 0},
            {0, 8, 23},
            {0, 23, 15},
            {0, 15, 7},
            {7, 15, 22, 14},
            {7, 14, 6},
            {6, 14, 21},
            {6, 21, 13},
            {6, 13, 5},
            {5, 13, 20, 12},
            {5, 12, 4},
            {4, 12, 19},
            {4, 19, 11},
            {4, 11, 3},
            {3, 11, 18, 10},
            {3, 2, 10},
            {2, 10, 17},
            {2, 17, 9},
            {16, 17, 9},
            {23, 16, 17, 24},
            {8, 16, 23},
            {15, 22, 23},
            {21, 22, 23, 24},
            {14, 21, 22},
            {13, 20, 21},
            {19, 20, 21, 24},
            {12, 19, 20},
            {11, 18, 19},
            {17, 18, 19, 24},
            {10, 17, 18}
    };

    public static Path createPath(int pathNum, int xOffset, int yOffset, float scale) {
        return Paths.makePath(paths[pathNum], xOffset, yOffset, scale);
    }
}
