package romine.colorwheel.Pieces;

import java.util.ArrayList;

/**
 * Created by karom on 10/20/2016.
 */

public class BigPiece extends SinglePiece {

    BigPiece(ArrayList<GamePiece>pieces, PieceColor color) {
        super(pieces, color);
    }

    static void addWithOffset(ArrayList<GamePiece> pieces, GamePiece piece, int xOffset, int yOffset) {
        piece.setOffsets(new Offset(xOffset, yOffset));
        pieces.add(piece);
    }
}
