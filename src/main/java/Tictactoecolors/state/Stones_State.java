package Tictactoecolors.state;

import lombok.Data;

@Data
public class Stones_State implements Cloneable{
    private static final char EMPTY = '_';
    private static char[][] init = new char[][] {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };


    private char[][] board;
    
    private int noOfFills;

    private Stones currentStone;

    private boolean gameOver;

    private Stones Winner;

    public Stones_State() {
        this(init);
    }

    public Stones_State(char[][] board) {
        if (!isBoardValid(board)){
            throw new IllegalArgumentException("Invalid Board");
        }
        
        setBoard(board);
        setGameOver(false);
        setWinner(null);
        setNoOfFills(0);
    }



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

    public void move(int row, int column){
        if (!(validMove(row, column) && checkBounds(row, column))){
            throw new IllegalMoveException("You can't make this move");
        }

        board[row][column] = getCurrentStone().getRepresentation();
        noOfFills++;
        currentStone.setNumberOfSteps(currentStone.getNumberOfSteps() + 1);
        checkIfGameOver(row, column);
        currentStone = currentStone.nextPlayer();
    }

    public void checkIfGameOver(int row, int column) {
        if (noOfFills == 9){
            setGameOver(true);
            setWinner(null);
        }
        else if(checkStreak(row, column)){
            setGameOver(true);
            setWinner(currentStone);
        }
        else{
            setGameOver(false);
        }
    }

    private boolean checkStreak(int row, int column) {
        return(checkRow(row) || checkColumn(column) || checkDiagonal1() || checkDiagonal2());
    }

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

    private boolean checkRow(int row) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < board[row].length ; i++) {
            builder.append(board[row][i]);
        }
        System.out.println(builder.toString());
        if (builder.toString().chars().filter(value -> value == getCurrentStone().getRepresentation()).count() >= 3 ){
            return true;
        }
        return false;
    }

    public boolean checkBounds(int row, int column) {
        if (!((row >= 0 && row < 3) && column >= 0 && column < 3)){
            return false;
        }
        return true;
    }

    public boolean validMove(int row, int column) {
        if (!(getBoard()[row][column] == EMPTY)){
            return false;
        }
//

        return true;
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
        //System.out.println();
        System.out.println(new Stones_State());
    }

}
