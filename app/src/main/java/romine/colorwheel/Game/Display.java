package romine.colorwheel.Game;

import android.graphics.Canvas;
import android.graphics.Color;

import romine.colorwheel.Board.DisplayBoard;
import romine.colorwheel.Board.GameBoard;
import romine.colorwheel.Board.SolutionBoard;
import romine.colorwheel.Pieces.BasePiece;

/**
 * Created by karom on 11/21/2016.
 */

public class Display {

    private int boardDimension;
    private float scale;
    private Canvas backgroundCanvas;
    private GameBoard gameBoard;
    private SolutionBoard solutionDisplay;
    private DisplayBoard pieceDisplay;
    private PuzzlePieces pieces;
    private int displayIndex;

    public Display(int dimension, float s, Puzzle puzzle, Canvas background) {
        scale = s;
        boardDimension = dimension;
        backgroundCanvas = background;
        newPuzzle(puzzle);
    }

    public void newPuzzle(Puzzle puzzle) {
        backgroundCanvas.drawColor(Color.WHITE);
        gameBoard = new GameBoard(3, 0, scale, boardDimension, backgroundCanvas);
        solutionDisplay = new SolutionBoard(scale*boardDimension/2 + 6, scale*boardDimension + 6, scale/2, puzzle.getSolution(), boardDimension, backgroundCanvas);
        pieceDisplay = new DisplayBoard(0, scale*boardDimension + 6, scale*boardDimension/4, 2, backgroundCanvas);
        puzzle.jumblePieces();
        pieces = new PuzzlePieces(puzzle.getSolution());
        displayIndex = 0;
        addDisplayPiece();
    }

    public void display() {
        gameBoard.display();
        pieceDisplay.display();
        solutionDisplay.display();
    }

    public void placePiece(BasePiece piece, float x, float y, float moveXOffset, float moveYOffset)  {
        float[] pos = this.getPosition(x, y);
        float[] displayPos = getPosition(x + scale/2, y + scale/2);

        if (pos[2] < 0) {
            pos[2] = scale + pos[2];
        }
        if (pos[3] < 0) {
            pos[3] = scale + pos[3];
        }
        if (pos[2] > scale/2) {
            pos[0]++;
        }
        if (pos[3] > scale/2) {
            pos[1]++;
        }

        if (onGameBoard(x + scale/2, y + scale/2) && gameBoard.pieceInRange(piece, (int)pos[0], (int)pos[1])) {
            addPieceToBoard(piece, (int)pos[0], (int)pos[1]);
        } else {
            returnPieceToDisplay(piece);
        }
    }

    public BasePiece canvasClick(float x, float y, float moveXOffset, float moveYOffset) {
        float[] pos = getPosition(x, y);
        if (onDisplayBoard(x, y)) {
            return canvasClickDisplayBoard(pieceDisplay.topPiece((int)pos[0], (int)pos[1], pos[2], pos[3]));
        } else if (onGameBoard(x, y)) {
            return canvasClickGameBoard(x, y, gameBoard.topPiece((int)pos[0], (int)pos[1], pos[2], pos[3]), moveXOffset, moveYOffset);
        } else {
            return null;
        }
    }

    private BasePiece canvasClickDisplayBoard(BasePiece piece) {
        if (piece != null) {
            removePieceFromDisplay(piece);
            addDisplayPiece();
        }
        return piece;
    }

    private BasePiece canvasClickGameBoard(float x, float y, BasePiece piece, float moveXOffset, float moveYOffset) {
        if (piece != null) {
            removePieceFromBoard(piece);
        }
        return piece;
    }

    public Boolean isFinished() {
        return solutionDisplay.match(gameBoard);
    }

    public float[] getOffsets(float x, float y) {
        float[] dimensions;
        if (onDisplayBoard(x, y)) {
            dimensions = getDisplayPositionalInformation(x, y);
        } else if (onGameBoard(x, y)) {
            dimensions = getBoardPositionalInformation(x, y);
        } else {
            return null;
        }
        float rslt[] = new float[2];
        rslt[0] = dimensions[0] * scale + dimensions[2];
        rslt[1] = dimensions[1] * scale + dimensions[3];
        return rslt;
    }

    private float[] getBoardPositionalInformation(float x, float y) {
        float[] pos = getPosition(x, y);
        BasePiece piece = gameBoard.topPiece((int)pos[0], (int)pos[1], pos[2], pos[3]);
        if (piece != null) {
            pos[0] -= piece.getXOffset();
            pos[1] -= piece.getYOffset();
        }
        return pos;
    }

    private float[] getDisplayPositionalInformation(float x, float y) {
        float[] pos = getPosition(x, y);
        float scale = gameBoard.getScale() / pieceDisplay.getScale();
        BasePiece piece = pieceDisplay.topPiece((int)pos[0], (int)pos[1], pos[2], pos[3]);
        if (piece != null) {
            pos[0] -= piece.getXOffset();
            pos[1] -= piece.getYOffset();
            pos[2] *= scale;
            pos[3] *= scale;
        }
        return pos;
    }

    public void searchPieceRight() {
        if (hasDisplayPiece()) {
            removeDisplayPiece();
            setPieceIndex((getPieceIndex() + 1) % pieces.count());
            addDisplayPiece();
        }
    }

    public void searchPieceLeft() {
        if (hasDisplayPiece()) {
            removeDisplayPiece();
            setPieceIndex(getPieceIndex() == 0 ? pieces.count() - 1 : getPieceIndex() - 1);
            addDisplayPiece();
        }
    }

    private float calcXOffset(float x, float y) {
        if (onDisplayBoard(x, y)) {
            return calcOffset(x, pieceDisplay.getXOffset(), this.pieceDisplay.getScale());
        } else {
            return calcOffset(x, gameBoard.getXOffset(), gameBoard.getScale());
        }
    }

    private float calcYOffset(float x, float y) {
        if (onDisplayBoard(x, y)) {
            return calcOffset(y, pieceDisplay.getYOffset(), this.pieceDisplay.getScale());
        } else {
            return calcOffset(y, gameBoard.getYOffset(), gameBoard.getScale());
        }
    }

    private float[] getPosition(float x, float y) {
        float[] position = new float[4];
        position[0] = calcXDimension(x, y);
        position[1] = calcYDimension(x, y);
        position[2] = calcXOffset(x, y);
        position[3] = calcYOffset(x, y);
        return position;
    }

    private float calcXDimension(float x, float y) {
        if (onDisplayBoard(x, y)) {
            return calcDimension(x, pieceDisplay.getXOffset(), this.pieceDisplay.getScale());
        } else {
            return calcDimension(x, gameBoard.getXOffset(), gameBoard.getScale());
        }
    }

    private float calcYDimension(float x, float y) {
        if (onDisplayBoard(x, y)) {
            return calcDimension(y, pieceDisplay.getYOffset(), this.pieceDisplay.getScale());
        } else {
            return calcDimension(y, gameBoard.getYOffset(), gameBoard.getScale());
        }
    }

    private float calcDimension(float i, float offset, float scale) {
        return (float)Math.floor((i-offset)/scale);
    }

    private float calcOffset(float i, float offset, float scale) {
        return (i - offset) % scale;
    }

    private boolean onGameBoard(float x, float y) {
        return gameBoard.onBoard(x, y);
    }

    private boolean onDisplayBoard(float x, float y) {
        return pieceDisplay.onBoard(x, y);
    }

    private boolean onSolutionDisplay(float x, float y) {
        return solutionDisplay.onBoard(x, y);
    }

    private void removePieceFromBoard(BasePiece piece) {
        gameBoard.removePiece(piece, piece.getXOffset(), piece.getYOffset());
    }

    private void returnPieceToDisplay(BasePiece piece) {
        removeDisplayPiece();
        pieces.addPiece(piece);
        this.displayIndex = pieces.indexOf(piece);
        piece.setOffsets(0, 0);
        addDisplayPiece();
    }

    private void addPieceToBoard(BasePiece piece, int x, int y) {
        gameBoard.addPiece(piece, x, y);
    }

    private void removePieceFromDisplay(BasePiece piece) {
        removeDisplayPiece();
        pieces.removePiece(piece);
        if (displayIndex == pieces.count()) {
            displayIndex = 0;
        }
        if (pieces.count() == 0) {
            displayIndex = -1;
        }
    }

    private BasePiece getDisplayPiece() {
        return displayIndex != -1 ? pieces.getPiece(getPieceIndex()) : null;
    }

    private boolean hasDisplayPiece() {
        return pieceDisplay.hasDisplayPiece();
    }

    private void removeDisplayPiece() {
        if (hasDisplayPiece()) {
            BasePiece piece = getDisplayPiece();
            pieceDisplay.removePiece(piece, piece.getXOffset(), piece.getYOffset());
        }
    }

    private void addDisplayPiece() {
        BasePiece piece = getDisplayPiece();
        if (hasDisplayPiece()) {
            return;
        }
        if (piece != null) {
            pieceDisplay.addPiece(piece, 0, 0);
        }
    }

    public void rotateRight() {
        pieceDisplay.rotateDisplayPieceRight();
    }

    public void rotateLeft() {
        pieceDisplay.rotateDisplayPieceLeft();
    }

    public void flipVertically() {
        pieceDisplay.flipDisplayPieceVertically();
    }

    public void flipHorizontally() {
        pieceDisplay.flipDisplayPieceHorizontally();
    }

    private int getPieceIndex() {
        return displayIndex;
    }

    private void setPieceIndex(int i) {
        displayIndex = i;
    }

}
