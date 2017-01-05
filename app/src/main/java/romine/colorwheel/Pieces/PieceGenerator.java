package romine.colorwheel.Pieces;

import java.util.ArrayList;

import romine.colorwheel.Game.Rand;

/**
 * Created by karom on 10/23/2016.
 */

public class PieceGenerator {

    final private double regularCutoff;

    public PieceGenerator(double regularProp) {
        this.regularCutoff = regularProp;
    }

    public BasePiece generatePiece() {
        double chance = Rand.randomProportion();
        if (chance < regularCutoff) {
            return regularPieceGenerator(null);
        } else  {
            return regularPieceGenerator(GamePiece.PieceColor.CLEAR);
        }
    }

    private BasePiece regularPieceGenerator(GamePiece.PieceColor color) {
        BasePiece piece;
        double chance;
        if (color == null) {
            chance = Rand.randomProportion();
            if (chance < 1.0/3) {
                color = GamePiece.PieceColor.RED;
            } else if (chance < 2.0/3) {
                color = GamePiece.PieceColor.YELLOW;
            } else {
                color = GamePiece.PieceColor.BLUE;
            }
        }
        if (Rand.randomProportion() < .5) {
            piece =  makeSmallPiece(color);
        } else {
            piece = makeBigPiece(color);
        }
        piece.jumblePiece();
        return piece;
    }

    private BasePiece makeSmallPiece(GamePiece.PieceColor color) {
        BasePiece piece;
        double chance;
        chance = Rand.randomProportion();
        if (chance < .25) {
            piece = new SmallSquare(color);
        } else if (chance < .5) {
            piece = new SmallIsoscelesTriangle(color);
        } else if (chance < .75) {
            piece = new SmallScaleneTriangle(color);
        } else {
            piece = new SmallTrapazoid(color);
        }
        piece.jumblePiece();
        return piece;
    }

    private BasePiece makeBigPiece(GamePiece.PieceColor color) {
        BasePiece piece;
        double chance = Rand.randomProportion();
        if (chance < .25) {
            piece = new BigSquare(color);
        } else if (chance < .5) {
            piece = new BigIsoscelesTriangle(color);
        } else if (chance < .75) {
            piece = new BigScaleneTriangle(color);
        } else {
            piece = new BigTrapazoid(color);
        }
        piece.jumblePiece();
        return piece;
    }
}
