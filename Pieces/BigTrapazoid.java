package romine.colorwheel.Pieces;

import java.util.ArrayList;

/**
 * Created by karom on 10/20/2016.
 */

public class BigTrapazoid extends BigPiece {

    BigTrapazoid(PieceColor color) {
        super(makeAtomicPieces(color), color);
    }

    static ArrayList<GamePiece> makeAtomicPieces(PieceColor color) {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        addWithOffset(pieces, new AtomicTrapazoidPiece(color), 1, 0);
        addWithOffset(pieces, new AtomicScaleneTrianglePiece(color), 1, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 0);
        return pieces;
    }
}
