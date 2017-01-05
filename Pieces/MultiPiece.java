package romine.colorwheel.Pieces;

import java.util.ArrayList;

/**
 * Created by karom on 10/20/2016.
 */

public class MultiPiece extends BasePiece {

    MultiPiece(ArrayList<GamePiece> pieces) {
        super(pieces);
    }

    public GamePiece clonePiece() {
        return new MultiPiece(this.copyPieces());
    }

}
