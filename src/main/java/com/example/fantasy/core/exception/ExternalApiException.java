package com.example.fantasy.core.exception;

/**
 * Exception thrown when there is an error communicating with external APIs.
 */
public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String message) {
        super(message);
    }
    
    public ExternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
