package com.example.fantasy.shared.utils;

import com.example.fantasy.core.common.ConsumerWithException;
import com.example.fantasy.core.common.FunctionWithException;
import com.example.fantasy.core.common.PredicateWithException;
import com.example.fantasy.core.common.SupplierWithException;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Utility class for handling exceptions in functional interfaces
 */
public final class ExceptionUtils {
    
    private ExceptionUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Wrap a PredicateWithException into a Predicate that handles the exception
     * @param predicateWithException the predicate that can throw an exception
     * @param exceptionHandler the handler for the exception
     * @param <T> the type of the input to the predicate
     * @param <E> the type of exception that may be thrown
     * @return a predicate that handles the exception
     */
    public static <T, E extends Exception> Predicate<T> wrapPredicate(
            PredicateWithException<T, E> predicateWithException,
            Function<E, Boolean> exceptionHandler) {
        
        return t -> {
            try {
                return predicateWithException.test(t);
            } catch (Exception e) {
                @SuppressWarnings("unchecked")
                E typedException = (E) e;
                return exceptionHandler.apply(typedException);
            }
        };
    }
    
    /**
     * Wrap a ConsumerWithException into a Consumer that handles the exception
     * @param consumerWithException the consumer that can throw an exception
     * @param exceptionHandler the handler for the exception
     * @param <T> the type of the input to the consumer
     * @param <E> the type of exception that may be thrown
     * @return a consumer that handles the exception
     */
    public static <T, E extends Exception> Consumer<T> wrapConsumer(
            ConsumerWithException<T, E> consumerWithException,
            Consumer<E> exceptionHandler) {
        
        return t -> {
            try {
                consumerWithException.accept(t);
            } catch (Exception e) {
                @SuppressWarnings("unchecked")
                E typedException = (E) e;
                exceptionHandler.accept(typedException);
            }
        };
    }
    
    /**
     * Wrap a FunctionWithException into a Function that handles the exception
     * @param functionWithException the function that can throw an exception
     * @param exceptionHandler the handler for the exception
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     * @param <E> the type of exception that may be thrown
     * @return a function that handles the exception
     */
    public static <T, R, E extends Exception> Function<T, R> wrapFunction(
            FunctionWithException<T, R, E> functionWithException,
            Function<E, R> exceptionHandler) {
        
        return t -> {
            try {
                return functionWithException.apply(t);
            } catch (Exception e) {
                @SuppressWarnings("unchecked")
                E typedException = (E) e;
                return exceptionHandler.apply(typedException);
            }
        };
    }
    
    /**
     * Wrap a SupplierWithException into a Supplier that handles the exception
     * @param supplierWithException the supplier that can throw an exception
     * @param exceptionHandler the handler for the exception
     * @param <T> the type of results supplied by the supplier
     * @param <E> the type of exception that may be thrown
     * @return a supplier that handles the exception
     */
    public static <T, E extends Exception> Supplier<T> wrapSupplier(
            SupplierWithException<T, E> supplierWithException,
            Function<E, T> exceptionHandler) {
        
        return () -> {
            try {
                return supplierWithException.get();
            } catch (Exception e) {
                @SuppressWarnings("unchecked")
                E typedException = (E) e;
                return exceptionHandler.apply(typedException);
            }
        };
    }
    
    /**
     * Wrap a runnable that can throw an exception into a Runnable that handles the exception
     * @param runnable the runnable that can throw an exception
     * @param exceptionHandler the handler for the exception
     * @param <E> the type of exception that may be thrown
     * @return a runnable that handles the exception
     */
    public static <E extends Exception> Runnable wrapRunnable(
            RunnableWithException<E> runnable,
            Consumer<E> exceptionHandler) {
        
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                @SuppressWarnings("unchecked")
                E typedException = (E) e;
                exceptionHandler.accept(typedException);
            }
        };
    }
    
    /**
     * A functional interface for a runnable that can throw an exception
     * @param <E> the type of exception that may be thrown
     */
    @FunctionalInterface
    public interface RunnableWithException<E extends Exception> {
        void run() throws E;
    }
}
