package xyz.bnayagrawal.game.rockpaperscissor.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameMove;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoveScore {

    private GameMove serverMove;
    private int userScore;
    private int serverScore;

    public MoveScore(GameMove serverMove, int userScore, int serverScore) {
        this.serverMove = serverMove;
        this.userScore = userScore;
        this.serverScore = serverScore;
    }

    // Only serialized when someone wins
    private String result;
}
