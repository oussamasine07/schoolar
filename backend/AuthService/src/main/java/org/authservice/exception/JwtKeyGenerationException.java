package org.authservice.exception;

public class JwtKeyGenerationException extends RuntimeException {
    public JwtKeyGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
