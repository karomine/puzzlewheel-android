package romine.colorwheel.Pieces;

/**
 * Created by karom on 10/18/2016.
 */

public class AtomicTrapazoidPiece extends AtomicGamePiece {

    private static int sections[][][][] = {
        {   //orientation inner
                {{2, 7},{23, 23},{0, 1},{19, 22},{24, 25},{8, 8},{9, 11},{26, 28},{30, 31},{18, 18}},           //corner topleft
                {{0, 2},{17, 20},{3, 3},{21, 22},{29, 31},{14, 16},{4, 6},{23, 25},{27, 28},{13, 13}},          //corner topright
                {{7, 12},{26,26},{4, 6},{23, 25},{27, 28},{13, 13},{3, 3},{21, 22},{29, 31},{14, 16}},          //corner bottomleft
                {{12, 17},{29, 29},{9, 11},{26, 28},{30, 31},{18, 18},{0, 1},{19, 22},{24, 25},{8, 8}}          //corner bottomright
        },
        {   //orientation outer
                {{0, 5},{22, 22},{19, 21},{23, 25},{6, 8},{9, 9},{26, 27},{29, 31},{16, 18}},                   //corner topleft
                {{15, 19},{31,31},{0, 0},{1, 3},{20, 22},{29, 30},{14, 14},{4, 4},{23, 24},{26, 28},{11, 13}},  //corner topright
                {{5,10},{25,25},{4,4},{23,24},{26,28},{11,13},{1,3},{20,22},{29,30},{14,14}},                   //corner bottomleft
                {{10,15},{28,28},{9,9},{26,27},{29,31},{16,18},{19,21},{23,25},{6,8}}                           //corner bottomright
        }
    };

    AtomicTrapazoidPiece(PieceColor color) {
        super(color);
    }

    GamePiece clonePiece() {
        return new AtomicTrapazoidPiece(this.color);
    }

    public int[][] getSections() {
        return sections[this.direction.ordinal()][this.corner.ordinal()];
    }
}
