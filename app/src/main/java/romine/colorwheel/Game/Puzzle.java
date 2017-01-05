package romine.colorwheel.Game;

import java.util.ArrayList;

import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 10/23/2016.
 */

public class Puzzle {

    private ArrayList<BasePiece> pieces;
    private int boardDimension;

    Puzzle(int boardDimension, ArrayList<BasePiece> solution) {
        this.boardDimension = boardDimension;
        this.pieces = solution;
        this.scramblePieces();
    }

    private void scramblePieces() {
        int random;
        ArrayList<BasePiece> scrambled = new ArrayList<>();
        while (pieces.size() > 0) {
            random = Rand.randomRange(0, pieces.size() - 1);
            scrambled.add(pieces.get(random));
            pieces.remove(random);
        }
        pieces = scrambled;
    }

    public void jumblePieces() {
        for (BasePiece piece : pieces) {
            piece.jumblePiece();
        }
    }

    public ArrayList<BasePiece> getSolution() {
        return pieces;
    }
}
