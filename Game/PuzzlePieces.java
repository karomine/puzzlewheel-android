package romine.colorwheel.Game;

import java.util.ArrayList;

import romine.colorwheel.Pieces.BasePiece;
import romine.colorwheel.Pieces.GamePiece;
import static romine.colorwheel.Pieces.GamePiece.PieceColor.NAN;

/**
 * Created by karom on 10/21/2016.
 */

public class PuzzlePieces {

    private ArrayList<BasePiece> pieces;

    PuzzlePieces(ArrayList<BasePiece> pieces) {
        this.pieces = pieces;
        if (!pieces.get(0).hasId()) {
            for (int i = 0; i < pieces.size(); i++) {
                pieces.get(0).setId(i+1);
            }
        }
    }

    public PuzzlePieces filter(GamePiece.PieceColor color) {
        if (color == NAN) {
            return new PuzzlePieces(this.pieces);
        }
        ArrayList<BasePiece> filteredPieces = new ArrayList<BasePiece>();
        for (BasePiece piece : this.pieces) {
            if (piece.hasColor(color)) {
                filteredPieces.add(piece);
            }
        }
        return new PuzzlePieces(filteredPieces);
    }

    public int count() {
        return this.pieces.size();
    }

    public void addPiece(BasePiece piece) {
        int i = 0;
        while (i < this.count() && piece.getId() > this.getPiece(i).getId()) {
            i++;
        }
        this.pieces.add(i, piece);
    }

    public void removePiece(BasePiece piece) {
        this.pieces.remove(piece);
    }

    private BasePiece getPiece(int i) {
        return this.pieces.get(i);
    }

    private int indexOf(BasePiece piece) {
        return this.pieces.indexOf(piece);
    }
}
