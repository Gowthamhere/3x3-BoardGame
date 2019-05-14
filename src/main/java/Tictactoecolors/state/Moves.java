package Tictactoecolors.state;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * A class that analyses the moves of the player.
 */
@Slf4j
public class Moves {

    private Scanner scanMove = new Scanner(System.in);


    public Moves() {
    }

    /**
     * Reads the moves of the player and check whether they are allowed to be played on the {@code BoardCell}.
     * @param state takes the instance of the {@code Stones_State} as the parameter
     * @return the {@code BoardCell} on which the player wants to play
     */

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
                log.error(state.getPlayer() + " had invalid number of arguments.");
                throw new IllegalArgumentException("Please check your argument");
            }

            int row = Integer.parseInt(input[0]);
            int column = Integer.parseInt(input[1]);

            if (!state.checkBounds(row, column)) {
                log.error(state.getPlayer() + " tried to input in a cell that is out of bonds");
                throw new IllegalMoveException("Out of Bounds");
            }

            if (!state.validMove(row, column)) {
                log.error(state.getPlayer() + " tried to input already filled place");
                throw new IllegalMoveException("Not a Valid Move");
            } else {
               return new BoardCell(row, column);
            }

        }catch (Exception ex){
            if (ex instanceof NumberFormatException){
                System.out.println("Please give a valid integer");
                log.error(state.getPlayer() + "gave a character instead of integer.");
            }
            else {
                ex.printStackTrace();
            }
            return null;
        }
    }
}
