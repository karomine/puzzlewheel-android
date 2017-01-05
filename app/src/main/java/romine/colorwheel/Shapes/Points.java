package romine.colorwheel.Shapes;

import android.util.Log;

/**
 * Created by karom on 10/19/2016.
 */

public class Points {

    private static final float[][] points = {
            {0,0},{.5f,0},{1,0},{1,.5f},{1,1},{.5f,1},
            {0,1},{0,.5f},{.4f,.2f},{.6f,.2f},{.8f,.4f},{.8f,.6f},
            {.6f,.8f},{.4f,.8f},{.2f,.6f},{.2f,.4f},{.5f,.25f},
            {2/3f,1/3f},{.75f,.5f},{2/3f,2/3f},{.5f,.75f},{1/3f,2/3f},
            {.25f,.5f},{1/3f,1/3f},{.5f,.5f}
    };

    public static float getXPoint(int index, float offset, float scale) {
        return offset + points[index][0] * scale;
    }

    public static float getYPoint(int index, float offset, float scale) {
        return offset + points[index][1] * scale;
    }

}
