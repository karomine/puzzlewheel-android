package romine.colorwheel.Pieces;

/**
 * Created by karom on 10/18/2016.
 */

public class AtomicIsoscelesTrianglePiece extends AtomicGamePiece {
    private static int[][][] sections = {
            {{19, 21},{23, 25},{6, 8},{0, 5},{22, 22}},             //corner topleft
            {{0, 2},{17, 20},{3, 3},{21, 22},{29, 31},{14, 16}},    //corner topright
            {{4, 4},{23, 24},{26, 28},{11, 13},{5, 10},{25, 25}},   //corner bottomleft
            {{12, 17},{29, 29},{9, 11},{26, 28},{30, 31},{18, 18}}  //corner bottomright
    };

    AtomicIsoscelesTrianglePiece(PieceColor color) {
        super(color);
    }

    GamePiece clonePiece() {
        return new AtomicIsoscelesTrianglePiece(this.color);
    }

    public int[][] getSections() {
        return sections[this.corner.ordinal()];
    }
}
