package org.schoolservice.exception;

public class UnauthorizedSchoolException extends RuntimeException {
    public UnauthorizedSchoolException(String message) {
        super(message);
    }
}
