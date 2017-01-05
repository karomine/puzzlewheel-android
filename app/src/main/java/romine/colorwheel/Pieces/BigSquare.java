package romine.colorwheel.Pieces;

import android.graphics.drawable.shapes.PathShape;

import java.util.ArrayList;

import romine.colorwheel.Shapes.MovingPiecePaths;


/**
 * Created by karom on 10/21/2016.
 */

public class BigSquare extends BigPiece {

    public BigSquare(PieceColor color) {
        super(makeAtomicPieces(color), color);
    }

    public PathShape getMovingShape(float xOffset, float yOffset, float scale) {
        return MovingPiecePaths.getSquarePath(xOffset, yOffset, scale*2);
    }

    static ArrayList<GamePiece> makeAtomicPieces(PieceColor color) {
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 0);
        addWithOffset(pieces, new AtomicSquarePiece(color), 0, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 1, 1);
        addWithOffset(pieces, new AtomicSquarePiece(color), 1, 0);
        return pieces;
    }

    public BasePiece clonePiece() {
        SinglePiece piece = new BigSquare(color);
        return reorient(piece);
    }

}
