package Tictactoecolors.state;
import Tictactoecolors.result.GameResult;
import Tictactoecolors.result.GameResultDao;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import util.guice.PersistenceModule;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Scanner;


/**
 * The main class of the game where the game can be played.
 */
@Slf4j
public class Game {

    /**
     * The main method of the class that allows the players to play the game
     * It takes players names and saves them in {@code Player1} and {@code Player2}
     * respectively
     * It allows the players to make the moves based on the {@code Stones_State} class and also
     * persists the winner and runner data into the database.
     * @param args
     */
    public static void main(String[] args) {
        Injector injector;
        GameResultDao gameResultDao;
        Stones_State state = new Stones_State();
        Scanner scanner = new Scanner(System.in);
        String Player1;
        String Player2;
        System.out.println("--------3 X 3 Board Game--------\n");
        System.out.print("Please choose a color to play with Red, Yellow, Green default is Red: ");
        switch (scanner.nextLine()) {
            case "red":
                state.setCurrentStone(Stones.P1_REDSTONE);
                break;
            case "yellow":
                state.setCurrentStone(Stones.P1_YELLOWSTONE);
                break;
            case "green":
                state.setCurrentStone(Stones.P1_GREENSTONE);
                break;
            default:
                state.setCurrentStone(Stones.P1_REDSTONE);
                break;
        }


        do {
            System.out.print("Player 1, enter your name: ");
            Player1 = scanner.nextLine();
            state.getCurrentStone();

        }while(Player1.equals(null) || Player1.length() < 1);
        state.setPlayer1(Player1);

        do {
            System.out.print("Player 2, enter your name: ");
            Player2 = scanner.nextLine();
            state.getCurrentStone().nextPlayer();
        }while(Player2.equals(null) || Player2.length() < 1);
        state.setPlayer2(Player2);

        log.info("Player 1 name: " + Player1);
        log.info("Player 2 name: " + Player2);

        System.out.println("\n" + state);
        Moves reader = new Moves();
        while (!state.isGameOver()) {
            BoardCell cells = null;

            do {
                System.out.printf("%s's move: ", state.getPlayer());
                cells = reader.readMove(state);

            }while (cells == null);
            state.move(cells.getRow(), cells.getColumn());
            System.out.println(state);
        }
        if (state.getWinner() != null) {
            state.setCurrentStone(state.getWinner());
            log.info(state.getPlayer() + " won with " + state.getPlayerMoves() + " moves.");

            injector = Guice.createInjector(new PersistenceModule("board_game"));
            gameResultDao = injector.getInstance(GameResultDao.class);
            GameResult winner = GameResult.builder()
                    .player(state.getPlayer())
                    .solved(true)
                    .moves(state.getPlayerMoves())
                    .timestamp(ZonedDateTime.now())
                    .build();
            gameResultDao.persist(winner);
            state.setCurrentStone(state.getRunner());
            GameResult runner = GameResult.builder()
                    .player(state.getPlayer())
                    .solved(false)
                    .moves(state.getPlayerMoves())
                    .timestamp(ZonedDateTime.now())
                    .build();
            gameResultDao.persist(runner);

            List<GameResult> highscore = gameResultDao.findBest(5);

            for (GameResult score : highscore){
                System.out.println(score);
            }

        }
        else {
            System.out.println("Draw");
        }

    }


}

