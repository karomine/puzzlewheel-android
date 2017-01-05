package romine.colorwheel.Pieces;

import android.graphics.drawable.shapes.PathShape;

import java.util.ArrayList;

import romine.colorwheel.Shapes.MovingPiecePaths;

/**
 * Created by karom on 10/20/2016.
 */

public class BigTrapazoid extends BigPiece {

    public BigTrapazoid(PieceColor color) {
        super(makeAtomicPieces(color), color);
    }

    public PathShape getMovingShape(float xOffset, float yOffset, float scale) {
        return MovingPiecePaths.getTrapazoidPath(xOffset, yOffset, scale*2, direction.ordinal(), corner.ordinal());
    }

    static ArrayList<GamePiece> makeAtomicPieces(PieceColor color) {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        addWithOffset(pieces, new AtomicTrapazoidPiece(color), 1, 0);
        addWithOffset(pieces, new AtomicScaleneTrianglePiece(color), 1, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 0);
        return pieces;
    }

    public BasePiece clonePiece() {
        SinglePiece piece = new BigTrapazoid(color);
        return reorient(piece);
    }
}
