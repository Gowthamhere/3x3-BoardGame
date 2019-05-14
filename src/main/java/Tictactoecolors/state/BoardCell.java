package Tictactoecolors.state;

/**
 * Class for initializing cells on the board.
 */

public class BoardCell {

    /**
     * The row location.
     */
    private int row;

    /**
     * The column location.
     */
    private int column;

    /**
     * The symbol representing empty space on the board.
     */
    public final char EMPTY = '_';

    public BoardCell() {
        this.row = 0;
        this.column = 0;
    }

    @Override
    public String toString() {
        return row + ", " + column;
    }

    public BoardCell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
