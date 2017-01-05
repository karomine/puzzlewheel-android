package romine.colorwheel.Pieces;

import java.util.ArrayList;

/**
 * Created by karom on 10/20/2016.
 */

public class SinglePiece extends BasePiece {

    PieceColor color;

    SinglePiece(ArrayList<GamePiece> pieces, PieceColor color) {
        super(pieces);
        this.color = color;
    }

    public GamePiece clonePiece() {
        return new SinglePiece(this.copyPieces(), this.color);
    }

    public boolean hasColor(PieceColor color) {
        return this.color == color;
    }
}
