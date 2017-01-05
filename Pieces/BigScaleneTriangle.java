package romine.colorwheel.Pieces;

import java.util.ArrayList;

/**
 * Created by karom on 10/21/2016.
 */

public class BigScaleneTriangle extends BigPiece {

    BigScaleneTriangle(PieceColor color) {
        super(makeAtomicPieces(color), color);
    }

    static ArrayList<GamePiece> makeAtomicPieces(PieceColor color) {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        addWithOffset(pieces, new AtomicTrapazoidPiece(color), 0, 0);
        addWithOffset(pieces, new AtomicScaleneTrianglePiece(color), 0, 1);
        return pieces;
    }
}
