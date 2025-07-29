package com.example.fantasy.core.common;

@FunctionalInterface
public interface FunctionalMapper<S, D> {
    
    /**
     * Maps the source object to the destination type
     * @param source the source object
     * @return the mapped destination object
     */
    D map(S source);
}
