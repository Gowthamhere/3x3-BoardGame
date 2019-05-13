package Tictactoecolors.state;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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

    @NotNull
    @Setter
    private int numberOfSteps;

    @NotNull
    @Setter
    private String player_Name;


    Stones(char letter) {
        this.representation = letter;
        this.numberOfSteps = 0;
    }

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

