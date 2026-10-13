
package edu.sfsu.csc413.chess.model;

public record Move(Position from, Position to, Piece moved,
                   Piece captured, PieceType promotesTo) {

    public static Move quiet(Position from, Position to, Piece moved) {
        return new Move(from, to, moved, null, null);
    }

    public static Move capture(Position from, Position to,
                               Piece moved, Piece captured) {
        return new Move(from, to, moved, captured, null);
    }

    public static Move promotion(Position from, Position to,
                                 Piece moved, Piece captured,
                                 PieceType promotesTo) {
        return new Move(from, to, moved, captured, promotesTo);
    }

    public boolean isCapture() {
        return captured != null;
    }

    public boolean isPromotion() {
        return promotesTo != null;
    }

    @Override
    public String toString() {
        String result = from.toString() + to.toString();

        if (isPromotion()) {
            result += Character.toLowerCase(promotesTo.symbol());
        }

        return result;
    }
}
