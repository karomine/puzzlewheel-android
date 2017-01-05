package romine.colorwheel.Pieces;

import android.graphics.drawable.shapes.PathShape;

import romine.colorwheel.Shapes.MovingPiecePaths;

/**
 * Created by karom on 10/20/2016.
 */

public class SmallTrapazoid extends SmallPiece {

    public SmallTrapazoid(PieceColor color) {
        super(new AtomicTrapazoidPiece(color), color);
    }

    public PathShape getMovingShape(float xOffset, float yOffset, float scale) {
        return MovingPiecePaths.getTrapazoidPath(xOffset, yOffset, scale, direction.ordinal(), corner.ordinal());
    }

    public BasePiece clonePiece() {
        SinglePiece piece = new SmallTrapazoid(color);
        return reorient(piece);
    }

}
