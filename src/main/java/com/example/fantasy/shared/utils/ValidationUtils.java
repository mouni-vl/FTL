package com.example.fantasy.shared.utils;

import com.example.fantasy.core.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Utility class for validation operations
 */
public final class ValidationUtils {
    
    private ValidationUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Validate an object against a set of validation rules
     * @param object the object to validate
     * @param validations the validation rules
     * @param <T> the type of the object
     * @throws ValidationException if validation fails
     */
    public static <T> void validate(T object, List<Validation<T>> validations) {
        List<String> errors = new ArrayList<>();
        
        for (Validation<T> validation : validations) {
            if (!validation.test(object)) {
                errors.add(validation.getMessage());
            }
        }
        
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
    
    /**
     * Create a validation rule
     * @param predicate the validation predicate
     * @param message the error message
     * @param <T> the type of the object
     * @return a validation rule
     */
    public static <T> Validation<T> rule(Predicate<T> predicate, String message) {
        return new Validation<>(predicate, message);
    }
    
    /**
     * Create a validation rule with a message supplier
     * @param predicate the validation predicate
     * @param messageSupplier the error message supplier
     * @param <T> the type of the object
     * @return a validation rule
     */
    public static <T> Validation<T> rule(Predicate<T> predicate, Supplier<String> messageSupplier) {
        return new Validation<>(predicate, messageSupplier.get());
    }
    
    /**
     * Class representing a validation rule
     * @param <T> the type of the object
     */
    public static class Validation<T> {
        private final Predicate<T> predicate;
        private final String message;
        
        private Validation(Predicate<T> predicate, String message) {
            this.predicate = predicate;
            this.message = message;
        }
        
        /**
         * Test if the object passes the validation
         * @param object the object to test
         * @return true if the object passes the validation, false otherwise
         */
        public boolean test(T object) {
            return predicate.test(object);
        }
        
        /**
         * Get the error message
         * @return the error message
         */
        public String getMessage() {
            return message;
        }
    }
}
