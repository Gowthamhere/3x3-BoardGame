package Tictactoecolors.state;

import lombok.extern.slf4j.Slf4j;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
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
            try {
                throw new IllegalStateException("Game Over");
            } catch (IllegalStateException e){
                System.err.println(e.getMessage());
            }
        }

        try {

            if (!scanMove.hasNextLine()) {
                return null;
            }

            String[] input = null;
            input = scanMove.nextLine().trim().split("\\s+");
            if (input.length != 2) {
                log.error("{} had invalid number of arguments.", state.getPlayer());
                try {
                    throw new IllegalArgumentException("Please check your argument");
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }

            int row = Integer.parseInt(input[0]);
            int column = Integer.parseInt(input[1]);

            if (!state.checkBounds(row, column)) {
                log.error("{} tried to input in a cell that is out of bonds", state.getPlayer());
                try {
                    throw new IllegalMoveException("Out of Bounds");
                }catch (IllegalMoveException e){
                    System.out.println(e.getMessage());
                }
            }

            if (!state.validMove(row, column)) {
                log.error("{} tried to input already filled place", state.getPlayer());
                try {
                    throw new IllegalMoveException("Not a Valid Move");
                }catch (IllegalMoveException e){
                    System.out.println(e.getMessage());
                    return null;
                }
            } else {
                return new BoardCell(row, column);
            }

        }catch (Exception ex){
            if (ex instanceof NumberFormatException){
                System.out.println("Please give a valid integer");
                log.error(state.getPlayer() + "gave a character instead of integer.");
            }
            else {
                System.out.println(ex.getMessage());
            }
            return null;
        }
    }
}
