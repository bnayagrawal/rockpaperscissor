package xyz.bnayagrawal.game.rockpaperscissor.util;

import xyz.bnayagrawal.game.rockpaperscissor.types.GameMove;

import java.util.Random;

public class GameMoveGenerator {

    public static GameMove makeRandomMove() {
        return GameMove.values()[new Random().nextInt(GameMove.values().length)];
    }
}
