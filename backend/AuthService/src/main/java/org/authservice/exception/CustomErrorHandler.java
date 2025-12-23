package org.authservice.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<Map<String, String>> handlePasswordIncorrectException (
            PasswordIncorrectException ex
    ) {
        System.out.println("CHECKING EXCEPTIONS: " + ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("status", "fail");
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException (
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(err -> {
                    String fieldName = ((FieldError) err).getField();
                    String msg = err.getDefaultMessage();
                    errors.put(fieldName, msg);
                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
















