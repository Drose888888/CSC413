package edu.sfsu.csc413.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The knight: two squares along one axis, one along the other, jumping over
 * anything in between.
 *
 * <p>The knight is the clearest illustration of why this design works. Its rule
 * is unlike any other piece's — it is the only one that ignores blocking
 * pieces — and yet it needs no special case anywhere else in the engine. It
 * simply answers the question every piece is asked.
 */
public class Knight extends Piece {

    /**
     * The eight L-shapes, as {file, rank} deltas.
     */
    private static final int[][] OFFSETS = { { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 } };

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }


    @Override
    public List<Move> pseudoLegalMoves(Board board, Position from) {
        List<Move> moves = new ArrayList<>();

        int[][] jumps = {
                {2, 1},
                {2, -1},
                {-2, 1},
                {-2, -1},
                {1, 2},
                {1, -2},
                {-1, 2},
                {-1, -2}
        };

        for (int[] jump : jumps) {
            int file = from.file() + jump[0];
            int rank = from.rank() + jump[1];

            if (file >= 0 && file < 8 && rank >= 0 && rank < 8) {
                Position to = new Position(file, rank);
                Piece target = board.pieceAt(to);

                if (target == null) {
                    moves.add(Move.quiet(from, to, this));
                } else if (target.color() != color()) {
                    moves.add(Move.capture(from, to, this, target));
                }
            }
        }

        return moves;
    }

}
