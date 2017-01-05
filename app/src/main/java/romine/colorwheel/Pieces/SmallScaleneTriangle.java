package romine.colorwheel.Pieces;

import android.graphics.drawable.shapes.PathShape;

import romine.colorwheel.Shapes.MovingPiecePaths;

/**
 * Created by karom on 10/20/2016.
 */

public class SmallScaleneTriangle extends SmallPiece {

    public SmallScaleneTriangle(PieceColor color) {
        super(new AtomicScaleneTrianglePiece(color), color);
    }

    public PathShape getMovingShape(float xOffset, float yOffset, float scale) {
        return MovingPiecePaths.getScaleneTrianglePath(xOffset, yOffset, scale, direction.ordinal(), corner.ordinal());
    }

    public BasePiece clonePiece() {
        SinglePiece piece = new SmallScaleneTriangle(color);
        return reorient(piece);
    }

}
