package Tictactoecolors.state;

import lombok.Data;

/**
 * A class representing the states of the stones.
 */
@Data
public class Stones_State implements Cloneable{
    /**
     * The Empty space character on the board
     */
    private static final char EMPTY = '_';

    /**
     * The initial setup of the board.
     */
    private static char[][] init = new char[][] {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };


    private char[][] board;

    /**
     * The number of stones on the board.
     */
    private int noOfFills;

    /**
     * The stone representing the current player.
     */
    private Stones currentStone;

    /**
     * Boolean value to check whether the game is over
     */
    private boolean gameOver;

    /**
     * The number of steps made by the 1st player initially set to 0.
     */
    private int player1steps = 0;

    /**
     * The number of steps made by the 2nd player initially set to 0.
     */
    private int player2steps = 0;

    /**
     * The name of 1st Player.
     */
    private String player1;

    /**
     * The name of 2nd Player.
     */
    private String player2;

    /**
     * A variable that stores the stone name of the winner.
     */
    private Stones Winner;

    /**
     * A variable that stores the stone name of the winner.
     */
    private Stones runner;

    /**
     * A constructor that sets the board to its initial state.
     */

    public Stones_State() {
        this(init);
    }

    /**
     * The constructor that takes the {@code board} as an argument and checks whether the board is valid.
     * @param board checks whether the board is valid
     * @throws IllegalArgumentException if the board is invalid
     */
    public Stones_State(char[][] board) {
        if (!isBoardValid(board)){
            throw new IllegalArgumentException("Invalid Board");
        }
        
        setBoard(init);
        setGameOver(false);
        setWinner(null);
        setRunner(null);
        setNoOfFills(0);
    }

    /**
     * A method that resets the {@code board} to its initial state.
     */
    public void reset(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '_';
            }
        }
    }

    /**
     * A method that returns the name of the current player.
     * @return the name of the current player
     */
    public String getPlayer(){
        if (getCurrentStone().equals(Stones.P1_REDSTONE) ||
                getCurrentStone().equals(Stones.P1_YELLOWSTONE) ||
                getCurrentStone().equals(Stones.P1_GREENSTONE)){

           return player1;
        }
        else{
            return player2;
        }
    }

    /**
     * A method that returns the number of moves made by the current player.
     * @return the number of moves made by the current player
     */
    public int getPlayerMoves(){
        if (getCurrentStone().equals(Stones.P1_REDSTONE) ||
                getCurrentStone().equals(Stones.P1_YELLOWSTONE) ||
                getCurrentStone().equals(Stones.P1_GREENSTONE)){

            return player1steps;
        }
        else{
            return player2steps;
        }
    }


    /**
     * Checks whether the board is valid and returns a true or false.
     * @param board if valid returns true else returns false
     * @return true if the board is valid, false otherwise
     */
    public boolean isBoardValid(char[][] board) {
        if(board == null || board.length != 3) {
            return false;
        }

        for(int i = 0; i < board.length; i++) {
            if(board[i] == null || board[i].length != 3) {
                return false;
            }
            for(int j= 0; j < board[i].length; j++) {
                if(board[i][j] != EMPTY) {
                    if (!(board[i][j] == Stones.P1_REDSTONE.getRepresentation() ||
                            board[i][j] == Stones.P1_YELLOWSTONE.getRepresentation() ||
                            board[i][j] == Stones.P1_GREENSTONE.getRepresentation() ||
                            board[i][j] == Stones.P2_REDSTONE.getRepresentation() ||
                            board[i][j] == Stones.P2_YELLOWSTONE.getRepresentation() ||
                            board[i][j] == Stones.P2_GREENSTONE.getRepresentation())) {

                        return false;
                    } else {
                        return isGameOver();
                    }
                }
            }
        }
        return true;
    }

    /**
     * A method to check whether the move made using the {@code currentStone} is valid or not.
     * If the move is valid then the {@code board} is marked with the name of the stone
     * and number of steps for the current player is incremented by 1
     * @param row checks the row for the possible move on {@code board}
     * @param column checks the row for the possible move on {@code board}
     */
    public void move(int row, int column){
        if (!(checkBounds(row, column))){
            throw new IllegalMoveException("You can't make this move");
        }

        if (!(validMove(row, column))){
            throw new IllegalMoveException("You can't make this move");
        }

        board[row][column] = getCurrentStone().getRepresentation();
        noOfFills++;
        if (getCurrentStone().equals(Stones.P1_REDSTONE) ||
                getCurrentStone().equals(Stones.P1_YELLOWSTONE) ||
                getCurrentStone().equals(Stones.P1_GREENSTONE)){

            player1steps++;
        }else{
            player2steps++;
        }

        checkIfGameOver(row, column);
        currentStone = currentStone.nextPlayer();
    }

    /**
     * A method to check whether the game is over. if the {@code noofFills} is 9 or if it's a streak then the game ends.
     * @param row checks the row if it is empty
     * @param column checks the column if it is empty
     */

    public void checkIfGameOver(int row, int column) {
        if (noOfFills == 9){
            setGameOver(true);
            setWinner(null);
            setRunner(null);

        }
        else if(checkStreak(row, column)){
            setGameOver(true);
            setWinner(currentStone);
            setRunner(currentStone.nextPlayer());
        }
        else{
            setGameOver(false);
        }
    }

    /**
     * A method to check if the player had a streak. If {@code Winner} had a streak in either row, or column or diagonal then it's a Win.
     * @param row checks the row for streak
     * @param column checks the column for streak
     * @return true if it's a streak, false otherwise
     */

    private boolean checkStreak(int row, int column) {
        return(checkRow(row) || checkColumn(column) || checkDiagonal1() || checkDiagonal2());
    }

    /**
     * A method to check for the streak in the diagonal from left to right.
     * @return true if it's a streak, false otherwise
     */
    private boolean checkDiagonal1() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            builder.append(board[i][i]);
        }

        if (builder.toString().chars().filter(value -> value == getCurrentStone().getRepresentation()).count() >= 3){
            return true;
        }

        return false;
    }

    /**
     * A method to check for the streak in the diagonal from right to left.
     * @return true if it's a streak false, otherwise
     */
    private boolean checkDiagonal2() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            builder.append(board[i][2-i]);
        }

        if (builder.toString().chars().filter(value -> value == getCurrentStone().getRepresentation()).count() >= 3){
            return true;
        }
        return false;
    }

    /**
     * A method to check for the streak in the column.
     * @param column checks for the streak in the column
     * @return true if it's a streak, false otherwise
     */
    private boolean checkColumn(int column) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            builder.append(board[i][column]);
        }
        if (builder.toString().chars().filter(value -> value == getCurrentStone().getRepresentation()).count() >= 3){
            return true;
        }
        return false;
    }

    /**
     * A method to check for the streak in the row.
     * @param row checks for the streak in the row
     * @return true if it's a streak, false otherwise
     */
    private boolean checkRow(int row) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board[row].length ; i++) {
            builder.append(board[row][i]);
        }
        if (builder.toString().chars().filter(value -> value == getCurrentStone().getRepresentation()).count() >= 3 ){
            return true;
        }
        return false;
    }

    /**
     * A method to check if the {@code currentStone} is placed inside the boundaries of the {@code board}.
     * @param row checks if the row is inside the boundary
     * @param column checks if the columns is inside the boundary
     * @return true if it is inside the boundary, false otherwise
     */
    public boolean checkBounds(int row, int column) {
        if (!(row >= 0 && row < 3 && column >= 0 && column < 3)){
            return false;
        }
        return true;
    }

    /**
     * A method to check if the cell in which the {@code currentStone} placed is empty or not.
     * @param row checks the row of the board to find whether it is empty or not
     * @param column checks the column of the board to find whether it is empty or not
     * @return true if it is a valid move, false otherwise
     */

    public boolean validMove(int row, int column) {
        if (board[row][column] == EMPTY){
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                builder.append(board[i][j]).append(' ');
            }

            builder.append('\n');
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Stones_State());
    }

}
