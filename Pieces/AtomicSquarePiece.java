package romine.colorwheel.Pieces;

/**
 * Created by karom on 10/18/2016.
 */

public class AtomicSquarePiece extends AtomicGamePiece {

    private static int[][] sections = {{0, 31}};

    AtomicSquarePiece(PieceColor color) {
        super(color);
    }

    GamePiece clonePiece() {
        return new AtomicSquarePiece(this.color);
    }

    public int[][] getSections() {
        return sections;
    }
}
