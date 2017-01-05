package romine.colorwheel.Pieces;

import java.util.ArrayList;

/**
 * Created by karom on 10/20/2016.
 */

abstract public class SinglePiece extends BasePiece {

    PieceColor color;

    public SinglePiece(ArrayList<GamePiece> pieces, PieceColor color) {
        super(pieces, color);
    }

    public boolean hasColor(PieceColor color) {
        return this.color == color;
    }
}
