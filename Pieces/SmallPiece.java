package romine.colorwheel.Pieces;

import java.util.ArrayList;


/**
 * Created by karom on 10/20/2016.
 */

public class SmallPiece extends SinglePiece {

    SmallPiece(AtomicGamePiece piece, PieceColor color) {
        super(makeAtomicPieces(piece), color);
    }

    static ArrayList<GamePiece> makeAtomicPieces(AtomicGamePiece piece) {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        pieces.add(piece);
        return pieces;
    }
}
