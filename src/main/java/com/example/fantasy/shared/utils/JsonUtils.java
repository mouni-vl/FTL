package com.example.fantasy.shared.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

/**
 * Utility class for JSON operations
 */
@Slf4j
public final class JsonUtils {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    private JsonUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Convert an object to JSON string
     * @param object the object to convert
     * @return the JSON string or empty if conversion fails
     */
    public static Optional<String> toJson(Object object) {
        try {
            return Optional.of(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON", e);
            return Optional.empty();
        }
    }
    
    /**
     * Convert a JSON string to an object
     * @param json the JSON string
     * @param clazz the class of the object
     * @param <T> the type of the object
     * @return the object or empty if conversion fails
     */
    public static <T> Optional<T> fromJson(String json, Class<T> clazz) {
        try {
            return Optional.of(objectMapper.readValue(json, clazz));
        } catch (IOException e) {
            log.error("Error converting JSON to object", e);
            return Optional.empty();
        }
    }
    
    /**
     * Parse a JSON string to a JsonNode
     * @param json the JSON string
     * @return the JsonNode or empty if parsing fails
     */
    public static Optional<JsonNode> parseJson(String json) {
        try {
            return Optional.of(objectMapper.readTree(json));
        } catch (IOException e) {
            log.error("Error parsing JSON", e);
            return Optional.empty();
        }
    }
    
    /**
     * Get the ObjectMapper instance
     * @return the ObjectMapper instance
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
