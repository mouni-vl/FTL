package com.example.fantasy.shared.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class for reflection operations
 */
@Slf4j
public final class ReflectionUtils {
    
    private ReflectionUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Get all fields of a class, including inherited fields
     * @param clazz the class to get fields from
     * @return a list of fields
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toList());
    }
    
    /**
     * Get all fields of a class and its superclasses
     * @param clazz the class to get fields from
     * @return a list of fields
     */
    public static List<Field> getAllFieldsIncludingInherited(Class<?> clazz) {
        List<Field> fields = getAllFields(clazz);
        
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && !superClass.equals(Object.class)) {
            fields.addAll(getAllFieldsIncludingInherited(superClass));
        }
        
        return fields;
    }
    
    /**
     * Get a field by name
     * @param clazz the class to get the field from
     * @param fieldName the name of the field
     * @return the field or empty if not found
     */
    public static Optional<Field> getField(Class<?> clazz, String fieldName) {
        try {
            return Optional.of(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !superClass.equals(Object.class)) {
                return getField(superClass, fieldName);
            }
            return Optional.empty();
        }
    }
    
    /**
     * Get a method by name and parameter types
     * @param clazz the class to get the method from
     * @param methodName the name of the method
     * @param parameterTypes the parameter types of the method
     * @return the method or empty if not found
     */
    public static Optional<Method> getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        try {
            return Optional.of(clazz.getDeclaredMethod(methodName, parameterTypes));
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !superClass.equals(Object.class)) {
                return getMethod(superClass, methodName, parameterTypes);
            }
            return Optional.empty();
        }
    }
    
    /**
     * Get the value of a field from an object
     * @param object the object to get the field value from
     * @param fieldName the name of the field
     * @return the field value or empty if not found or accessible
     */
    public static Optional<Object> getFieldValue(Object object, String fieldName) {
        return getField(object.getClass(), fieldName).map(field -> {
            try {
                field.setAccessible(true);
                return Optional.ofNullable(field.get(object));
            } catch (IllegalAccessException e) {
                log.error("Error accessing field: {}", fieldName, e);
                return Optional.empty();
            }
        }).orElse(Optional.empty());
    }
    
    /**
     * Set the value of a field in an object
     * @param object the object to set the field value in
     * @param fieldName the name of the field
     * @param value the value to set
     * @return true if the field was set, false otherwise
     */
    public static boolean setFieldValue(Object object, String fieldName, Object value) {
        return getField(object.getClass(), fieldName).map(field -> {
            try {
                field.setAccessible(true);
                field.set(object, value);
                return true;
            } catch (IllegalAccessException e) {
                log.error("Error setting field: {}", fieldName, e);
                return false;
            }
        }).orElse(false);
    }
}
