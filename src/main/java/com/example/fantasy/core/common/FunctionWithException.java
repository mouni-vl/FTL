package com.example.fantasy.core.common;

/**
 * A functional interface for a function that can throw an exception
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @param <E> the type of exception that may be thrown
 */
@FunctionalInterface
public interface FunctionWithException<T, R, E extends Exception> {
    
    /**
     * Applies this function to the given argument
     * @param t the function argument
     * @return the function result
     * @throws E if an error occurs
     */
    R apply(T t) throws E;
    
    /**
     * Returns a composed function that first applies this function to its input, and then applies
     * the after function to the result
     * @param <V> the type of output of the after function, and of the composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then applies the after function
     */
    default <V> FunctionWithException<T, V, E> andThen(FunctionWithException<? super R, ? extends V, E> after) {
        return t -> after.apply(apply(t));
    }
    
    /**
     * Returns a composed function that first applies the before function to its input, and then
     * applies this function to the result
     * @param <V> the type of input to the before function, and to the composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the before function and then applies this function
     */
    default <V> FunctionWithException<V, R, E> compose(FunctionWithException<? super V, ? extends T, E> before) {
        return v -> apply(before.apply(v));
    }
}
