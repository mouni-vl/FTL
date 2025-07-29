package com.example.fantasy.core.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    
    private final List<String> errors;
    
    public ValidationException(String message) {
        super(message);
        this.errors = new ArrayList<>();
        this.errors.add(message);
    }
    
    public ValidationException(List<String> errors) {
        super(String.join(", ", errors));
        this.errors = errors;
    }
    
    public ValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
