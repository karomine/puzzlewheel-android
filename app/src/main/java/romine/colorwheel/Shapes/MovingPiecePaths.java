package romine.colorwheel.Shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.PathShape;

/**
 * Created by karom on 10/19/2016.
 */

public class MovingPiecePaths {

    private static final int[] squarePath = {0, 2, 4, 6};
    private static final int[][][] scaleneTrianglePaths = {
            {   //orientation inner
                    {6, 0, 1},  //corner top left
                    {0, 3, 2},  //corner top right
                    {4, 7, 6},  //corner bottom left
                    {2, 5, 4}   //corner bottom right
            },
            {   //orientation outer
                    {2, 0, 7},  //corner top left
                    {4, 1, 2},  //corner top right
                    {0, 6, 5},  //corner bottom left
                    {6, 3, 4}   //corner bottom right
            }
    };
    private static final int[][][] bigScaleneTrianglePaths = {
            {   //orientation inner
                    {6, 0, 1},  //corner top left
                    {0, 3, 2},  //corner top right
                    {0, 7, 3},  //corner bottom left
                    {6, 5, 1}   //corner bottom right
            },
            {   //orientation outer
                    {2, 0, 7},  //corner top left
                    {0, 1, 5},  //corner top right
                    {0, 6, 5},  //corner bottom left
                    {7, 3, 2}   //corner bottom right
            }
    };
    private static final int[][] isoscelesTrinaglePaths = {
            {6, 0, 2},  //corner top left
            {0, 2, 4},  //corner top right
            {0, 6, 4},  //corner bottom left
            {6, 2, 4}   //corner bottom right
    };
    private static final int[][][] trapazoidPaths = {
            {   //orientation inner
                    {6, 0, 2, 5},   //corner top left
                    {0, 2, 4, 7},   //corner top right
                    {0, 3, 4, 6},   //corner bottom left
                    {6, 1, 2, 4}    //corner bottom right
            },
            {   //orientation outer
                    {6, 0, 2, 3},   //corner top left
                    {0, 2, 4, 5},   //corner top right
                    {0, 1, 4, 6},   //corner bottom left
                    {6, 7, 2, 4}    //corner bottom right
            }
    };

    public static PathShape getSquarePath(float xOffset, float yOffset, float scale) {
        PathShape shape = new PathShape(Paths.makePath(squarePath, xOffset, yOffset, scale), 1, 1);
        shape.resize(1, 1);
        return shape;
    }

    public static PathShape getScaleneTrianglePath(float xOffset, float yOffset, float scale,  int orientation, int corner) {
        PathShape shape = new PathShape(Paths.makePath(scaleneTrianglePaths[orientation][corner], xOffset, yOffset, scale), 1, 1);
        shape.resize(1, 1);
        return shape;
    }

    public static PathShape getBigScaleneTrianglePath(float xOffset, float yOffset, float scale, int orientation, int corner) {
        PathShape shape = new PathShape(Paths.makePath(bigScaleneTrianglePaths[orientation][corner], xOffset, yOffset, scale), 1, 1);
        shape.resize(1, 1);
        return shape;
    }

    public static PathShape getIsoscelesTrianglePath(float xOffset, float yOffset, float scale, int corner) {
        PathShape shape = new PathShape(Paths.makePath(isoscelesTrinaglePaths[corner], xOffset, yOffset, scale), 1, 1);
        shape.resize(1, 1);
        return shape;
    }

    public static PathShape getTrapazoidPath(float xOffset, float yOffset, float scale, int orientation, int corner) {
        PathShape shape = new PathShape(Paths.makePath(trapazoidPaths[orientation][corner], xOffset, yOffset, scale), 1, 1);
        shape.resize(1, 1);
        return shape;
    }
}
