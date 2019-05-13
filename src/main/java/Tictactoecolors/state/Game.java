package Tictactoecolors.state;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Stones_State state = new Stones_State();
        Scanner scanner = new Scanner(System.in);
        String Player1;
        String Player2;
        System.out.println("--------3 X 3 Board Game--------\n");
        System.out.print("Please choose a color to play with Red, Yellow, Green: ");
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
        }

        do {
            System.out.print("Player 1, enter your name: ");
            Player1 = scanner.nextLine();
            state.getCurrentStone().setPlayer_Name(Player1);

        }while(Player1.equals(null) || Player1.length() < 1);

        do {
            System.out.print("Player 2, enter your name: ");
            Player2 = scanner.nextLine();
            state.getCurrentStone().nextPlayer().setPlayer_Name(Player2);
        }while(Player2.equals(null) || Player2.length() < 1);


        System.out.println("\n" + state);
        Moves reader = new Moves();
        while (!state.isGameOver()) {
            BoardCell cells = null;

            do {
                System.out.printf("%s's move: ", state.getCurrentStone().getPlayer_Name());
                cells = reader.readMove(state);

            }while (cells == null);
            state.move(cells.getRow(), cells.getColumn());
            System.out.println(state);
        }
        if (state.getWinner() != null) {
            System.out.println(state.getWinner().getPlayer_Name() + " won");
        }
        else {
            System.out.println("Draw");
        }
}
}
