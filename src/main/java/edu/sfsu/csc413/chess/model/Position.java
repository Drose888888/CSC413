package edu.sfsu.csc413.chess.model;

public record Position(int file, int rank) {

    public static final int BOARD_SIZE = 8;

    public static boolean isOnBoard(int file, int rank) {
        if (file >= 0 && file < 8 && rank >= 0 && rank < 8) {
            return true;
        }

        return false;
    }

    public Position {
        if (!isOnBoard(file, rank)) {
            throw new IllegalArgumentException("Position is not on the board");
        }
    }

    public static Position parse(String algebraic) {
        char fileLetter = algebraic.charAt(0);
        char rankNumber = algebraic.charAt(1);

        int file = fileLetter - 'a';
        int rank = rankNumber - '1';

        return new Position(file, rank);
    }

    public Position offsetOrNull(int fileDelta, int rankDelta) {
        int newFile = file + fileDelta;
        int newRank = rank + rankDelta;

        if (isOnBoard(newFile, newRank)) {
            return new Position(newFile, newRank);
        }

        return null;
    }

    @Override
    public String toString() {
        char fileLetter = (char) ('a' + file);
        char rankNumber = (char) ('1' + rank);

        return "" + fileLetter + rankNumber;
    }
}