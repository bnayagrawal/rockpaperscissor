package xyz.bnayagrawal.game.rockpaperscissor.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Player {

    @Id
    @GenericGenerator(name = "token_generator", strategy = "xyz.bnayagrawal.game.rockpaperscissor.util.TokenGenerator")
    @GeneratedValue(generator = "token_generator")
    private String id;
    private Integer attempts;
    private Integer userScore;
    private Integer serverScore;

    /**
     * Create a fresh new player with no attempts
     * @return A new Player
     */
    public static Player createNewPlayer() {
        Player player = new Player();
        player.attempts = 0;
        player.userScore = 0;
        player.serverScore = 0;
        return player;
    }
}
