package Tictactoecolors.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GameResult {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String player;

    @Column(nullable = false)
    private boolean solved;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private ZonedDateTime timestamp;

    @PrePersist
    protected void onPersist() {
        timestamp = ZonedDateTime.now();
    }


}
