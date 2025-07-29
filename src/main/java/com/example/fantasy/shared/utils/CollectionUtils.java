package com.example.fantasy.shared.utils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Utility class for collection operations
 */
public final class CollectionUtils {
    
    private CollectionUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Check if a collection is null or empty
     * @param collection the collection to check
     * @param <T> the type of elements in the collection
     * @return true if the collection is null or empty, false otherwise
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }
    
    /**
     * Check if a collection is not null and not empty
     * @param collection the collection to check
     * @param <T> the type of elements in the collection
     * @return true if the collection is not null and not empty, false otherwise
     */
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }
    
    /**
     * Filter a collection by a predicate
     * @param collection the collection to filter
     * @param predicate the predicate to filter by
     * @param <T> the type of elements in the collection
     * @return a new list containing only the elements that match the predicate
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        if (isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    
    /**
     * Map a collection to a new collection using a function
     * @param collection the collection to map
     * @param mapper the function to map with
     * @param <T> the type of elements in the input collection
     * @param <R> the type of elements in the output collection
     * @return a new list containing the mapped elements
     */
    public static <T, R> List<R> map(Collection<T> collection, Function<T, R> mapper) {
        if (isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
    
    /**
     * Convert a collection to a map using a key extractor
     * @param collection the collection to convert
     * @param keyExtractor the function to extract keys
     * @param <T> the type of elements in the collection
     * @param <K> the type of keys in the map
     * @return a map with keys extracted from the collection elements
     */
    public static <T, K> Map<K, T> toMap(Collection<T> collection, Function<T, K> keyExtractor) {
        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream()
                .collect(Collectors.toMap(keyExtractor, Function.identity(), (a, b) -> a));
    }
    
    /**
     * Convert a collection to a map using a key extractor and a value extractor
     * @param collection the collection to convert
     * @param keyExtractor the function to extract keys
     * @param valueExtractor the function to extract values
     * @param <T> the type of elements in the collection
     * @param <K> the type of keys in the map
     * @param <V> the type of values in the map
     * @return a map with keys and values extracted from the collection elements
     */
    public static <T, K, V> Map<K, V> toMap(Collection<T> collection, 
                                           Function<T, K> keyExtractor,
                                           Function<T, V> valueExtractor) {
        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream()
                .collect(Collectors.toMap(keyExtractor, valueExtractor, (a, b) -> a));
    }
    
    /**
     * Group a collection by a key extractor
     * @param collection the collection to group
     * @param keyExtractor the function to extract keys
     * @param <T> the type of elements in the collection
     * @param <K> the type of keys in the map
     * @return a map with keys extracted from the collection elements and values as lists of elements
     */
    public static <T, K> Map<K, List<T>> groupBy(Collection<T> collection, Function<T, K> keyExtractor) {
        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream()
                .collect(Collectors.groupingBy(keyExtractor));
    }
}
