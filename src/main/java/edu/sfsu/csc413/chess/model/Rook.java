package edu.sfsu.csc413.chess.model;
import java.util.ArrayList;
import java.util.List;

/**
 * The rook: slides any distance along ranks and files.
 */
public class Rook extends Piece {

    /**
     * The four straight directions, as {file, rank} deltas.
     */
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }


    @Override
    public List<Move> pseudoLegalMoves(Board board, Position from) {
        List<Move> moves = new ArrayList<>();

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
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
