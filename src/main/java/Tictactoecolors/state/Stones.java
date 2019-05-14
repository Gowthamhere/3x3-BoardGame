package Tictactoecolors.state;

import lombok.Getter;


/**
 * Class representing the possible stones for each player.
 */
@Getter
public enum Stones {
    PLAYER_1('P'),
    PLAYER_2('p'),
    P1_REDSTONE('R'),
    P1_YELLOWSTONE('Y'),
    P1_GREENSTONE('G'),
    P2_REDSTONE('r'),
    P2_YELLOWSTONE('y'),
    P2_GREENSTONE('g'),
    DRAW('D');

    private char representation;


    Stones(char letter) {
        this.representation = letter;
    }

    /**
     * A method that returns the next player.
     * @return the next player
     * @throws AssertionError if there is no valid opponent
     */

    public Stones nextPlayer() {
        switch (this) {
            case P1_REDSTONE:
                return P2_REDSTONE;
            case P2_REDSTONE:
                return P1_REDSTONE;
            case P1_YELLOWSTONE:
                return P2_YELLOWSTONE;
            case P2_YELLOWSTONE:
                return P1_YELLOWSTONE;
            case P1_GREENSTONE:
                return P2_GREENSTONE;
            case P2_GREENSTONE:
                return P1_GREENSTONE;
        }

        throw new AssertionError("Not a valid Player");
    }

}

