package com.example.fantasy.core.common;

import com.example.fantasy.core.exception.ValidationException;

@FunctionalInterface
public interface FunctionalValidator<T> {
    
    /**
     * Validates the input
     * @param input the input to validate
     * @throws ValidationException if validation fails
     */
    void validate(T input) throws ValidationException;
}
