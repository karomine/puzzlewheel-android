package romine.colorwheel.Pieces;

import android.graphics.drawable.shapes.PathShape;

import romine.colorwheel.Shapes.MovingPiecePaths;

/**
 * Created by karom on 10/20/2016.
 */

public class SmallSquare extends SmallPiece {

    public SmallSquare(PieceColor color) {
        super(new AtomicSquarePiece(color), color);
    }

    public PathShape getMovingShape(float xOffset, float yOffset, float scale) {
        return MovingPiecePaths.getSquarePath(xOffset, yOffset, scale);
    }

    public BasePiece clonePiece() {
        SinglePiece piece = new SmallSquare(color);
        return reorient(piece);
    }
}
