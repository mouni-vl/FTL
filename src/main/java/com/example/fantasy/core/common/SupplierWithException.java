package com.example.fantasy.core.common;

/**
 * A functional interface for a supplier that can throw an exception
 * @param <T> the type of results supplied by this supplier
 * @param <E> the type of exception that may be thrown
 */
@FunctionalInterface
public interface SupplierWithException<T, E extends Exception> {
    
    /**
     * Gets a result
     * @return a result
     * @throws E if an error occurs
     */
    T get() throws E;
}
