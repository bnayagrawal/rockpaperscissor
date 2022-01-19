package xyz.bnayagrawal.game.rockpaperscissor.exception;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class InvalidTokenException extends Exception {

    private final String message;

    public InvalidTokenException() {
        super("Invalid token");
        this.message = super.getMessage();
    }
}
