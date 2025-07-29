package com.example.fantasy.core.common;

/**
 * A functional interface for a predicate that can throw an exception
 * @param <T> the type of the input to the predicate
 * @param <E> the type of exception that may be thrown
 */
@FunctionalInterface
public interface PredicateWithException<T, E extends Exception> {
    
    /**
     * Evaluates this predicate on the given argument
     * @param t the input argument
     * @return true if the input argument matches the predicate, otherwise false
     * @throws E if an error occurs
     */
    boolean test(T t) throws E;
    
    /**
     * Returns a composed predicate that represents a short-circuiting logical AND of this
     * predicate and another
     * @param other a predicate that will be logically-ANDed with this predicate
     * @return a composed predicate that represents the short-circuiting logical AND of this
     * predicate and the other predicate
     */
    default PredicateWithException<T, E> and(PredicateWithException<? super T, E> other) {
        return t -> test(t) && other.test(t);
    }
    
    /**
     * Returns a composed predicate that represents a short-circuiting logical OR of this
     * predicate and another
     * @param other a predicate that will be logically-ORed with this predicate
     * @return a composed predicate that represents the short-circuiting logical OR of this
     * predicate and the other predicate
     */
    default PredicateWithException<T, E> or(PredicateWithException<? super T, E> other) {
        return t -> test(t) || other.test(t);
    }
    
    /**
     * Returns a predicate that represents the logical negation of this predicate
     * @return a predicate that represents the logical negation of this predicate
     */
    default PredicateWithException<T, E> negate() {
        return t -> !test(t);
    }
}
