package Tictactoecolors.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Class representing the result of the game.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GameResult {

    @Id
    @GeneratedValue
    private int id;

    /**
     * The name of the player.
     */
    @Column(nullable = false)
    private String player;

    /**
     * Whether the game is solved.
     */
    @Column(nullable = false)
    private boolean solved;

    /**
     * Number of moves made by the player.
     */
    @Column(nullable = false)
    private int moves;

    /**
     * The timestamp of the players.
     */
    @Column(nullable = false)
    private ZonedDateTime timestamp;

    @PrePersist
    protected void onPersist() {
        timestamp = ZonedDateTime.now();
    }


}
