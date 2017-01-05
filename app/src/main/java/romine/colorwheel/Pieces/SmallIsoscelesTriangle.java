package romine.colorwheel.Pieces;

import android.graphics.drawable.shapes.PathShape;

import romine.colorwheel.Shapes.MovingPiecePaths;

/**
 * Created by karom on 10/20/2016.
 */

public class SmallIsoscelesTriangle extends SmallPiece {

    public SmallIsoscelesTriangle(PieceColor color) {
        super(new AtomicIsoscelesTrianglePiece(color), color);
    }

    public PathShape getMovingShape(float xOffset, float yOffset, float scale) {
        return MovingPiecePaths.getIsoscelesTrianglePath(xOffset, yOffset, scale, corner.ordinal());
    }

    public BasePiece clonePiece() {
        SinglePiece piece = new SmallIsoscelesTriangle(color);
        return reorient(piece);
    }

}
