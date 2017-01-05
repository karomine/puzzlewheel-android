package romine.colorwheel.Pieces;

import android.graphics.drawable.shapes.PathShape;

import java.util.ArrayList;

import romine.colorwheel.Shapes.MovingPiecePaths;

/**
 * Created by karom on 10/21/2016.
 */

public class BigIsoscelesTriangle extends BigPiece {

    public BigIsoscelesTriangle(PieceColor color) {
        super(makeAtomicPieces(color), color);
    }

    public PathShape getMovingShape(float xOffset, float yOffset, float scale) {
        return MovingPiecePaths.getIsoscelesTrianglePath(xOffset, yOffset, scale*2, corner.ordinal());
    }

    static ArrayList<GamePiece> makeAtomicPieces(PieceColor color) {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 0);
        addWithOffset(pieces, new AtomicIsoscelesTrianglePiece(color), 1, 0);
        addWithOffset(pieces, new AtomicIsoscelesTrianglePiece(color), 0, 1);
        return pieces;
    }

    public BasePiece clonePiece() {
        SinglePiece piece = new BigIsoscelesTriangle(color);
        return reorient(piece);
    }

}
