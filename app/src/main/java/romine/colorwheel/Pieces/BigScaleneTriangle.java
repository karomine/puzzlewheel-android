package romine.colorwheel.Pieces;

import android.graphics.drawable.shapes.PathShape;

import java.util.ArrayList;

import romine.colorwheel.Shapes.MovingPiecePaths;

/**
 * Created by karom on 10/21/2016.
 */

public class BigScaleneTriangle extends BigPiece {

    public BigScaleneTriangle(PieceColor color) {
        super(makeAtomicPieces(color), color);
    }

    public PathShape getMovingShape(float xOffset, float yOffset, float scale) {
        return MovingPiecePaths.getBigScaleneTrianglePath(xOffset, yOffset, scale*2, direction.ordinal(), corner.ordinal());
    }

    static ArrayList<GamePiece> makeAtomicPieces(PieceColor color) {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        addWithOffset(pieces, new AtomicTrapazoidPiece(color), 0, 0);
        addWithOffset(pieces, new AtomicScaleneTrianglePiece(color), 0, 1);
        return pieces;
    }

    public BasePiece clonePiece() {
        SinglePiece piece = new BigScaleneTriangle(color);
        return reorient(piece);
    }
}
