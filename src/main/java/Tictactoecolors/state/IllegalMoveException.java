package Tictactoecolors.state;

/**
 * An Exception class that can be thrown whenever an illegal move is made.
 */
public class IllegalMoveException extends IllegalArgumentException {
    public IllegalMoveException(String message) {
        super(message);
    }
}
