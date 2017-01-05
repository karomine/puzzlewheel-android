package romine.colorwheel.Pieces;

import java.util.ArrayList;


/**
 * Created by karom on 10/20/2016.
 */

public abstract class SmallPiece extends SinglePiece {

    public SmallPiece(AtomicGamePiece piece, PieceColor color) {
        super(makeAtomicPieces(piece), color);
    }

    static ArrayList<GamePiece> makeAtomicPieces(AtomicGamePiece piece) {
        ArrayList<GamePiece> pieces = new ArrayList<>();
        pieces.add(piece);
        return pieces;
    }
}
