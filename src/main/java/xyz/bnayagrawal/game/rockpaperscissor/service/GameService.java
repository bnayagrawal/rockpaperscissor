package xyz.bnayagrawal.game.rockpaperscissor.service;

import xyz.bnayagrawal.game.rockpaperscissor.exception.InvalidTokenException;
import xyz.bnayagrawal.game.rockpaperscissor.model.dto.MoveScore;
import xyz.bnayagrawal.game.rockpaperscissor.model.dto.StartGame;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameMove;

public interface GameService {

    StartGame startGame();

    MoveScore makeMove(String token, GameMove userMove) throws InvalidTokenException;

    MoveScore makeServerWin(String token, GameMove userMove) throws InvalidTokenException;
}
