package com.example.fantasy.core.exception;

public class ResourceNotFoundException extends DomainException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resourceName, Object key) {
        super(String.format("%s not found with positionId: %s", resourceName, key));
    }
    
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
    }
}
