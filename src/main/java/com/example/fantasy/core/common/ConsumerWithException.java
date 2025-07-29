package com.example.fantasy.core.common;

/**
 * A functional interface for a consumer that can throw an exception
 * @param <T> the type of the input to the consumer
 * @param <E> the type of exception that may be thrown
 */
@FunctionalInterface
public interface ConsumerWithException<T, E extends Exception> {
    
    /**
     * Performs this operation on the given argument
     * @param t the input argument
     * @throws E if an error occurs
     */
    void accept(T t) throws E;
    
    /**
     * Returns a composed Consumer that performs, in sequence, this operation followed by the after operation
     * @param after the operation to perform after this operation
     * @return a composed Consumer that performs in sequence this operation followed by the after operation
     */
    default ConsumerWithException<T, E> andThen(ConsumerWithException<? super T, E> after) {
        return t -> {
            accept(t);
            after.accept(t);
        };
    }
}
