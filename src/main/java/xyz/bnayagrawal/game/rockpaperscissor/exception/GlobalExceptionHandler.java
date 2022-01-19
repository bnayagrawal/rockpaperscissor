package xyz.bnayagrawal.game.rockpaperscissor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import xyz.bnayagrawal.game.rockpaperscissor.model.dto.Error;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> methodArgumentTypMismatchExceptionHandler() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Please check your request format!"));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Error> invalidTokenExceptionHandler(InvalidTokenException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(e.getMessage()));
    }
}
