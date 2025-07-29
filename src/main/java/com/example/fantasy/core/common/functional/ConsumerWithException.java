package com.example.fantasy.core.common.functional;

import com.example.fantasy.core.exception.DomainException;

@FunctionalInterface
public interface ConsumerWithException<T> {
    
    /**
     * Performs this operation on the given input
     * @param t the input to consume
     * @throws DomainException if the operation fails with an exception
     */
    void accept(T t) throws DomainException;
}
