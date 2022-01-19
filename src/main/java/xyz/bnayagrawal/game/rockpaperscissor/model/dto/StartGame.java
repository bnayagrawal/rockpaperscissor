package xyz.bnayagrawal.game.rockpaperscissor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.bnayagrawal.game.rockpaperscissor.types.GameStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartGame {

    private String token;
    private GameStatus status;
}
