package com.example.fantasy.core.common.functional;

import com.example.fantasy.core.exception.DomainException;

@FunctionalInterface
public interface PredicateWithException<T> {
    
    /**
     * Tests the predicate on the given input
     * @param t the input to test
     * @return true if the predicate passes, false otherwise
     * @throws DomainException if the predicate fails with an exception
     */
    boolean test(T t) throws DomainException;
}
