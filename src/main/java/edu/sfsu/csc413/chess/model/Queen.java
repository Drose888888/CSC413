package edu.sfsu.csc413.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The queen: slides along both the straight and the diagonal directions.
 *
 * <p>A queen moves like a rook and a bishop combined, and Java's single
 * inheritance will not let it extend both. That limitation is worth sitting
 * with, because the instinct it provokes — "then let me borrow their direction
 * tables" — is the wrong one. An earlier version of this class did exactly
 * that, deriving its eight directions from {@code Rook.DIRECTIONS} and
 * {@code Bishop.DIRECTIONS}. It worked, and it was clever, and it cost more
 * than it saved:
 *
 * <ul>
 *   <li>both sibling classes had to loosen their tables from {@code private} to
 *       package-private, so a detail of how a rook moves became visible to
 *       every class in the package;
 *   <li>the arrays were <em>shared</em>, not copied — Java arrays cannot be made
 *       immutable, so any one of the three classes could have scribbled on data
 *       the other two depend on;
 *   <li>and a reader asking the simple question "which way does a queen move?"
 *       had to open two other files to find out.
 * </ul>
 *
 * <p>Eight literal pairs answer that question on sight, and they do not couple
 * this class to anything. Duplication is not free, but it is cheaper here than
 * the coupling it would have bought us — which is the judgement call the DRY
 * principle actually asks you to make, rather than the reflex it is often
 * mistaken for.
 */
public class Queen extends Piece {

    /**
     * All eight directions: the four straight and the four diagonal.
     */
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }


    @Override
    public List<Move> pseudoLegalMoves(Board board, Position from) {
        List<Move> moves = new ArrayList<>();

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };

        for (int[] direction : directions) {
            int file = from.file() + direction[0];
            int rank = from.rank() + direction[1];

            while (file >= 0 && file < 8 && rank >= 0 && rank < 8) {
                Position to = new Position(file, rank);
                Piece target = board.pieceAt(to);

                if (target == null) {
                    moves.add(Move.quiet(from, to, this));
                } else {
                    if (target.color() != color()) {
                        moves.add(Move.capture(from, to, this, target));
                    }
                    break;
                }

                file += direction[0];
                rank += direction[1];
            }
        }

        return moves;
    }

}
