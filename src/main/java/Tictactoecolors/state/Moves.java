package Tictactoecolors.state;

import java.util.Scanner;

public class Moves {

    private Scanner scanMove = new Scanner(System.in);


    public Moves() {
    }

    public BoardCell readMove(Stones_State state){

        if (state.isGameOver()){
            throw new IllegalStateException("Game Over!!!");
        }

        try {

            if (!scanMove.hasNextLine()) {
                return null;
            }

            String[] input = null;
            input = scanMove.nextLine().trim().split("\\s+");
            if (input.length != 2) {
                throw new IllegalArgumentException("Please check your argument");
            }

            int row = Integer.parseInt(input[0]);
            int column = Integer.parseInt(input[1]);

            if (!state.checkBounds(row, column)) {
                throw new IllegalMoveException("Out of Bounds");
            }

            if (!state.validMove(row, column)) {
                throw new IllegalMoveException("Not a Valid Move");
            } else {
               return new BoardCell(row, column);
            }

        }catch (Exception ex){
            if (ex instanceof NumberFormatException){
                System.out.println("Please give a valid integer");
            }
            else {
                ex.printStackTrace();
            }
            return null;
        }
    }
}
