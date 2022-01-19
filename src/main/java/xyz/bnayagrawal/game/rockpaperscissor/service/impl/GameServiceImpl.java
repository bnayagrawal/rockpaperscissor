package xyz.bnayagrawal.game.rockpaperscissor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.bnayagrawal.game.rockpaperscissor.exception.InvalidTokenException;
import xyz.bnayagrawal.game.rockpaperscissor.helper.GameHelper;
import xyz.bnayagrawal.game.rockpaperscissor.model.domain.Player;
import xyz.bnayagrawal.game.rockpaperscissor.model.dto.MoveScore;
import xyz.bnayagrawal.game.rockpaperscissor.model.dto.StartGame;
import xyz.bnayagrawal.game.rockpaperscissor.repository.PlayerRepository;
import xyz.bnayagrawal.game.rockpaperscissor.service.GameService;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameMove;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameMoveResult;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameStatus;
import xyz.bnayagrawal.game.rockpaperscissor.util.GameMoveGenerator;
import xyz.bnayagrawal.game.rockpaperscissor.values.GameScore;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final PlayerRepository playerRepository;
    private final GameHelper gameScoreHelper;

    @Override
    public StartGame startGame() {
        Player player = playerRepository.save(Player.createNewPlayer());
        return new StartGame(player.getId(), GameStatus.Ready);
    }

    @Override
    public MoveScore makeMove(String token, GameMove userMove) throws InvalidTokenException {
        // Get player
        Optional<Player> o = playerRepository.findById(token);
        if(o.isEmpty()) throw new InvalidTokenException();
        Player player = o.get();
        // Make server move
        GameMove serverMove = GameMoveGenerator.makeRandomMove();
        // Update score
        updatePlayerScore(player, userMove, serverMove);

        // Only store result if game ends (Ends when someone wins)
        String gameResult = null;
        // Check if someone has already won
        final int userScore = player.getUserScore();
        final int serverScore = player.getServerScore();
        if(userScore == GameScore.MAX_SCORE || serverScore == GameScore.MAX_SCORE) {
            gameResult = "Game finished. " + (userScore == GameScore.MAX_SCORE ? "User won!" : "Server won!");
            // Delete player from DB
            playerRepository.delete(player);
        } else {
            // Save player score info
            playerRepository.save(player);
        }

        return new MoveScore(serverMove, userScore, serverScore, gameResult);
    }

    @Override
    public MoveScore makeServerWin(String token, GameMove userMove) throws InvalidTokenException {
        // Get player
        Optional<Player> o = playerRepository.findById(token);
        if(o.isEmpty()) throw new InvalidTokenException();
        Player player = o.get();
        // Server move is always Paper!
        GameMove serverMove = GameMove.Paper;
        // Update score
        updatePlayerScore(player, userMove, serverMove);

        // Only store result if game ends (Ends when someone wins)
        String gameResult = null;
        // Game only ends if server's score is more than MAX_SCORE and also more than user's score!
        final int userScore = player.getUserScore();
        final int serverScore = player.getServerScore();
        if(userScore >= GameScore.MAX_SCORE && serverScore > userScore) {
            gameResult = "Game finished. Server won!";
            // Delete player from DB
            playerRepository.delete(player);
        } else {
            // Save player score info
            playerRepository.save(player);
        }

        return new MoveScore(serverMove, userScore, serverScore, gameResult);
    }

    private void updatePlayerScore(Player player, GameMove userMove, GameMove serverMove) {
        // Determine result
        GameMoveResult result = gameScoreHelper.getMoveResult(userMove, serverMove);

        // Determine score
        int userScore = player.getUserScore() + (result == GameMoveResult.UserWon ? GameScore.WIN_SCORE : GameScore.LOSS_OR_TIE_SCORE);
        int serverScore = player.getServerScore() + (result == GameMoveResult.ServerWon ? GameScore.WIN_SCORE : GameScore.LOSS_OR_TIE_SCORE);

        // Update score result
        player.setUserScore(userScore);
        player.setServerScore(serverScore);
    }
}
