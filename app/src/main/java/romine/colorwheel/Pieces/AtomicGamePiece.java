package romine.colorwheel.Pieces;

import java.util.ArrayList;

import romine.colorwheel.Board.Board;
import romine.colorwheel.Board.GridTile;

/**
 * Created by karom on 10/18/2016.
 */

public abstract class AtomicGamePiece extends GamePiece {

    PieceColor color;

    AtomicGamePiece(PieceColor color) {
        super();
        this.color = color;
    }

    AtomicGamePiece(PieceColor color, Offset offset) {
        super(offset);
    }

    public abstract int[][] getSections();

    void removePiece(GridTile[][] tiles, int xOffset, int yOffset, BasePiece parentPiece) {
        tiles[xOffset][yOffset].removePieceInRanges(getSections(), this, parentPiece);
    }
    void addPiece(GridTile[][] tiles, int xOffset, int yOffset, BasePiece parentPiece) {
        tiles[xOffset][yOffset].addPieceInRanges(getSections(), this, parentPiece);
    }

    public ArrayList<GamePiece> getPieces() {
        ArrayList<GamePiece> pieces = new ArrayList<>();
        pieces.add(this);
        return pieces;
    }

    public boolean hasColor(PieceColor color) {
        return this.color == color;
    }

    public void updateBoard(Board board, int xOffset, int yOffset) {
        board.getBoardGrid()[xOffset][yOffset].display();
    }

    public PieceColor getColor() {
        return this.color;
    }
}
