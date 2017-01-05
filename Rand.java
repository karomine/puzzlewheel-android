package romine.colorwheel;

import java.util.Random;

/**
 * Created by karom on 10/23/2016.
 */

public class Rand {

    private static Random rand = new Random();

    public static int randomRange(int start, int end) {
        return rand.nextInt() % (end - start + 1) + start;
    }

    public static double randomProportion() {
        return rand.nextDouble();
    }

}
