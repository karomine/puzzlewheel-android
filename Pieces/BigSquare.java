package romine.colorwheel.Pieces;

import java.util.ArrayList;


/**
 * Created by karom on 10/21/2016.
 */

public class BigSquare extends BigPiece {

    BigSquare(PieceColor color) {
        super(makeAtomicPieces(color), color);
    }

    static ArrayList<GamePiece> makeAtomicPieces(PieceColor color) {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 1, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 1, 0);
        return pieces;
    }

}
