package romine.colorwheel.Pieces;

import android.graphics.Paint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import romine.colorwheel.Board.PaintColors;
import romine.colorwheel.Rand;

/**
 * Created by karom on 10/23/2016.
 */

public class PieceGenerator {

    final private int boardDimension;
    final private double regularCutoff;
    final private double clearCutOff;
    final private double multiClearProp;
    private ArrayList<ArrayList<ArrayList<Offset>>> offsets;

    public PieceGenerator(int boardDimension, double regularProp, double clearProp, double multiClearProp) {
        this.boardDimension = boardDimension;
        this.regularCutoff = regularProp;
        this.clearCutOff = regularCutoff + clearProp;
        this.multiClearProp = multiClearProp;
    }

    public BasePiece generatePiece() {
        double chance = Rand.randomProportion();
        if (chance < regularCutoff) {
            return regularPieceGenerator(null);
        } else if (chance < clearCutOff) {
            return regularPieceGenerator(GamePiece.PieceColor.CLEAR);
        } else {
            return randomMultiPiece();
        }
    }

    private BasePiece regularPieceGenerator(GamePiece.PieceColor color) {
        BasePiece piece;
        double chance;
        if (color == null) {
            chance = Rand.randomProportion();
            if (chance < 1/3) {
                color = GamePiece.PieceColor.RED;
            } else if (chance < 2/3) {
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
        return BasePiece.jumblePiece(piece);
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
        return BasePiece.jumblePiece(piece);
    }

    private BasePiece makeBigPiece(GamePiece.PieceColor color) {
        BasePiece piece;
        double chance;
        chance = Rand.randomProportion();
        if (chance < .25) {
            piece = new BigSquare(color);
        } else if (chance < .5) {
            piece = new BigIsoscelesTriangle(color);
        } else if (chance < .75) {
            piece = new BigScaleneTriangle(color);
        } else {
            piece = new BigTrapazoid(color);
        }
        return BasePiece.jumblePiece(piece);
    }

    private MultiPiece randomMultiPiece() {
        int pieceXDimension = Rand.randomRange(2, boardDimension);
        int pieceYDimension = Rand.randomRange(2, boardDimension);
        int boardSize = pieceXDimension * pieceYDimension;
        if (boardSize <= 4) {
            return createMultiPiece(0, pieceXDimension, pieceYDimension);
        } else if (boardSize <= 16) {
            return createMultiPiece(Rand.randomRange(0, 1), pieceXDimension, pieceYDimension);
        } else if (boardSize <= 25) {
            return createMultiPiece(Rand.randomRange(0, 2), pieceXDimension, pieceYDimension);
        } else {
            return createMultiPiece(Rand.randomRange(0, 3), pieceXDimension, pieceYDimension);
        }
    }

    private MultiPiece createMultiPiece(int numBig, int xDimension, int yDimension) {
        int numSmall;
        if (numBig == 0) {
            numSmall = Rand.randomRange(2, (int) Math.floor(boardDimension / 4 + 3));
        } else if (numBig == 1) {
            numSmall = Rand.randomRange(1, (int) Math.floor((boardDimension - 4) / 2));
        } else {
            numSmall = Rand.randomRange(0, (int) Math.floor((boardDimension - 8) / 3));
        }
        ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
        createSmallPieces(pieces, numSmall);
        createBigPieces(pieces, numBig);
        createOffsets(xDimension, yDimension);
        return new MultiPiece(pieces);
    }

    private void createOffsets(int xDimension, int yDimension) {
        offsets = new ArrayList<ArrayList<ArrayList<Offset>>>();
        offsets.add(new ArrayList<ArrayList<Offset>>());
        offsets.add(new ArrayList<ArrayList<Offset>>());
        offsets.get(0).add(makeOffsets(xDimension, yDimension));
        offsets.get(0).add(makeOffsets(xDimension, yDimension - 1));
        offsets.get(1).add(makeOffsets(xDimension - 1, yDimension));
        offsets.get(1).add(makeOffsets(xDimension - 1, yDimension - 1));
    }

    private void  createSmallPieces(ArrayList<GamePiece> pieces, int num) {
        GamePiece piece;
        for (int i = 0; i < num; i++) {
            piece = makeSmallPiece(getChanceForClear());
            piece.setOffsets(getRandomOffset(piece.getXOffset(), piece.getYOffset()));
            pieces.add(piece);
        }
    }

    private void createBigPieces(ArrayList<GamePiece> pieces, int num) {
        GamePiece piece;
        for (int i = 0; i < num; i++) {
            piece = makeBigPiece(getChanceForClear());
            piece.setOffsets(getRandomOffset(piece.getXOffset(), piece.getYOffset()));
        }
    }

    private void setAnchor(ArrayList<GamePiece> pieces) {
        while (noOffsetXZero(pieces)) {
            for (GamePiece piece : pieces) {
                piece.setXOffset(piece.getXOffset() - 1);
            }
        }
        while (noOffsetYZero(pieces)) {
            for (GamePiece piece : pieces) {
                piece.setYOffset(piece.getYDimension() - 1);
            }
        }
    }
    private boolean noOffsetXZero(ArrayList<GamePiece> pieces) {
        for (GamePiece piece : pieces) {
            if (piece.getXOffset() == 0) {
                return false;
            }
        }
        return true;
    }
    private boolean noOffsetYZero(ArrayList<GamePiece> pieces) {
        for (GamePiece piece : pieces) {
            if (piece.getYOffset() == 0) {
                return false;
            }
        }
        return true;
    }

    private GamePiece.PieceColor getChanceForClear() {
        return Rand.randomProportion() < multiClearProp ? GamePiece.PieceColor.CLEAR : null;
    }

    private Offset getRandomOffset(int xDimension, int yDimension) {
        ArrayList<Offset> offsetList = offsets.get(xDimension).get(yDimension);
        Offset anchor = offsetList.get(Rand.randomRange(0, offsetList.size() - 1));

        removeAroundAnchor(anchor);
        if (xDimension > 0) {
            removeAroundAnchor(new Offset(anchor.getXOffset() + 1, anchor.getYOffset()));
        }
        if (yDimension > 0) {
            removeAroundAnchor(new Offset(anchor.getXOffset(), anchor.getYOffset() + 1));
        }
        if (xDimension > 0 && yDimension > 0) {
            removeAroundAnchor(new Offset(anchor.getXOffset() + 1, anchor.getYOffset() + 1));
        }
        return anchor;
    }
    private void removeAroundAnchor(Offset anchor) {
        removeFromAllOffsets(anchor);

        removeFromOneByTwo(new Offset(anchor.getXOffset(), anchor.getYOffset() - 1));
        removeFromOneByTwo(new Offset(anchor.getXOffset() - 1, anchor.getYOffset()));

        removeFromTwoByTwo(new Offset(anchor.getXOffset() - 1, anchor.getYOffset() - 1));
        removeFromTwoByTwo(new Offset(anchor.getXOffset() - 1, anchor.getYOffset()));
        removeFromTwoByTwo(new Offset(anchor.getXOffset(), anchor.getYOffset() - 1));
    }

    ArrayList<Offset> makeOffsets(int xDimension, int yDimension) {
        ArrayList<Offset> offsets = new ArrayList<Offset>();
        for (int i = 0; i < xDimension; i++) {
            for (int j = 0; j < yDimension; j++) {
                offsets.add(new Offset(i, j));
            }
        }
        return offsets;
    }

    private void removeOffset(ArrayList<Offset> offsetIndex, Offset offset) {
        for (int i = 0; i < offsetIndex.size(); i++) {
            if (offsetIndex.get(i).getXOffset() == offset.getXOffset() &&
                    offsetIndex.get(i).getYOffset() == offset.getYOffset()) {
                offsetIndex.remove(i);
                return;
            }
        }
    }

    private void removeFromOneByOne(Offset offset) {
        removeOffset(offsets.get(0).get(0), offset);
    }
    private void removeFromOneByTwo(Offset offset) {
        removeOffset(offsets.get(0).get(1), offset);
    }
    private void removeFromTwoByOne(Offset offset) {
        removeOffset(offsets.get(1).get(0), offset);
    }
    private void removeFromTwoByTwo(Offset offset) {
        removeOffset(offsets.get(1).get(1), offset);
    }
    private void removeFromAllOffsets(Offset offset) {
        removeFromOneByOne(offset);
        removeFromOneByTwo(offset);
        removeFromTwoByOne(offset);
        removeFromTwoByTwo(offset);
    }


}
