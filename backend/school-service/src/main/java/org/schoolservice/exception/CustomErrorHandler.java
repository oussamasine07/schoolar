package org.schoolservice.exception;

import org.flywaydb.core.api.FlywayException;
import org.springframework.dao.DataAccessException;
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

    @ExceptionHandler(FlywayException.class)
    public ResponseEntity<Map<String, String>> handleFlywayException ( FlywayException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> handleDataAccessException ( DataAccessException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException ( RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException (NotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
