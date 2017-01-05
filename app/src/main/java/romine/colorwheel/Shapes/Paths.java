package romine.colorwheel.Shapes;

import android.graphics.Path;

/**
 * Created by karom on 10/19/2016.
 */

public class Paths {

    static Path makePath(int[] path, float xOffset, float yOffset, float scale) {
        android.graphics.Path p = new android.graphics.Path();
        p.moveTo(Points.getXPoint(path[0], xOffset, scale), Points.getYPoint(path[0], yOffset, scale));
        for (int i = 1; i < path.length; i++) {
            p.lineTo(Points.getXPoint(path[i], xOffset, scale), Points.getYPoint(path[i], yOffset, scale));
        }
        p.close();
        return p;
    }
}
