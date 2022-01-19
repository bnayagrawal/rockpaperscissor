package xyz.bnayagrawal.game.rockpaperscissor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.bnayagrawal.game.rockpaperscissor.exception.InvalidTokenException;
import xyz.bnayagrawal.game.rockpaperscissor.model.dto.MoveScore;
import xyz.bnayagrawal.game.rockpaperscissor.model.dto.StartGame;
import xyz.bnayagrawal.game.rockpaperscissor.service.GameService;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameMove;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("start")
    public StartGame startGame() {
        return gameService.startGame();
    }

    @GetMapping("v1/{token}/{move}")
    public MoveScore makeMove(@PathVariable String token, @PathVariable GameMove move) throws InvalidTokenException {
        return gameService.makeMove(token, move);
    }

    @GetMapping("v2/{token}/{move}")
    public MoveScore makeServerWin(@PathVariable String token, @PathVariable GameMove move) throws InvalidTokenException {
        return gameService.makeServerWin(token, move);
    }
}