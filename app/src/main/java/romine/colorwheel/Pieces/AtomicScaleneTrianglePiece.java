package romine.colorwheel.Pieces;

/**
 * Created by karom on 10/18/2016.
 */

public class AtomicScaleneTrianglePiece extends AtomicGamePiece {

    private static int[][][][] sections = {
            { //orientation inner
                    {{2, 7},{23, 23}},      //corner topleft
                    {{0, 2},{17, 20}},      //corner topright
                    {{7, 12},{26, 26}},     //corner bottomleft
                    {{12, 17},{29, 29}}     //corner bottomright
            },
            { //orientation outer
                    {{0, 5},{22, 22}},          //corner topleft
                    {{15, 19},{31, 31},{0, 0}}, //corner topright
                    {{5, 10},{25, 25}},         //corner bottomleft
                    {{10, 15},{28, 28}}         //corner bottomright
            }
    };


    AtomicScaleneTrianglePiece(PieceColor color) {
        super(color);
    }

    GamePiece clonePiece() {
        return new AtomicScaleneTrianglePiece(this.color);
    }

    public int[][] getSections() {
        return sections[this.direction.ordinal()][this.corner.ordinal()];
    }
}
