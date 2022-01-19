package xyz.bnayagrawal.game.rockpaperscissor.helper;

import org.springframework.stereotype.Component;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameMove;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameMoveResult;

@Component
public class GameHelper {

    public GameMoveResult getMoveResult(GameMove userMove, GameMove serverMove) {
        if(userMove == serverMove)
            return GameMoveResult.Tie;

        switch (serverMove) {
            case Rock:
                return userMove == GameMove.Paper ? GameMoveResult.UserWon : GameMoveResult.ServerWon;
            case Paper:
                return userMove == GameMove.Scissor ? GameMoveResult.UserWon : GameMoveResult.ServerWon;
            case Scissor:
                return userMove == GameMove.Rock ? GameMoveResult.UserWon : GameMoveResult.ServerWon;
        }

        throw new RuntimeException("Invalid move");
    }
}
