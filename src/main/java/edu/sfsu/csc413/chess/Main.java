
package edu.sfsu.csc413.chess;

import edu.sfsu.csc413.chess.model.Board;
import edu.sfsu.csc413.chess.model.Color;
import edu.sfsu.csc413.chess.model.Piece;
import edu.sfsu.csc413.chess.model.PieceType;
import edu.sfsu.csc413.chess.model.Position;
import edu.sfsu.csc413.chess.model.Bishop;
import edu.sfsu.csc413.chess.model.King;
import edu.sfsu.csc413.chess.model.Knight;
import edu.sfsu.csc413.chess.model.Pawn;
import edu.sfsu.csc413.chess.model.Queen;
import edu.sfsu.csc413.chess.model.Rook;
import edu.sfsu.csc413.chess.view.PieceGlyphs;
import edu.sfsu.csc413.chess.view.TextBoardRenderer;

public final class Main {

    public static void main(String[] args) {

        Board board = new Board();

        PieceType[] backRow = {
                PieceType.ROOK,
                PieceType.KNIGHT,
                PieceType.BISHOP,
                PieceType.QUEEN,
                PieceType.KING,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.ROOK
        };

        for (int file = 0; file < Position.BOARD_SIZE; file++) {

            board.place(
                    new Position(file, 0),
                    makePiece(Color.WHITE, backRow[file])
            );

            board.place(
                    new Position(file, 1),
                    new Pawn(Color.WHITE)
            );

            board.place(
                    new Position(file, 6),
                    new Pawn(Color.BLACK)
            );

            board.place(
                    new Position(file, 7),
                    makePiece(Color.BLACK, backRow[file])
            );
        }

        System.out.println(
                new TextBoardRenderer(PieceGlyphs.LETTERS).render(board)
        );
    }

    private static Piece makePiece(Color color, PieceType type) {
        return switch (type) {
            case ROOK -> new Rook(color);
            case KNIGHT -> new Knight(color);
            case BISHOP -> new Bishop(color);
            case QUEEN -> new Queen(color);
            case KING -> new King(color);
            case PAWN -> new Pawn(color);
        };
    }

    private Main() {
    }
}
