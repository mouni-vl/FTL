package com.example.fantasy.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Configuration for resilience patterns (retry, circuit breaker) for external API calls.
 */
@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofMillis(10000))
            .permittedNumberOfCallsInHalfOpenState(2)
            .slidingWindowSize(10)
            .build();
        
        return CircuitBreakerRegistry.of(circuitBreakerConfig);
    }
    
    @Bean
    public CircuitBreaker fixtureApiCircuitBreaker(CircuitBreakerRegistry registry) {
        return registry.circuitBreaker("fixtureApi");
    }
    
    @Bean
    public CircuitBreaker playerApiCircuitBreaker(CircuitBreakerRegistry registry) {
        return registry.circuitBreaker("playerApi");
    }
    
    @Bean
    public CircuitBreaker teamApiCircuitBreaker(CircuitBreakerRegistry registry) {
        return registry.circuitBreaker("teamApi");
    }
    
    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig retryConfig = RetryConfig.custom()
            .maxAttempts(3)
            .waitDuration(Duration.ofMillis(1000))
            .build();
        
        return RetryRegistry.of(retryConfig);
    }
    
    @Bean
    public Retry fixtureApiRetry(RetryRegistry registry) {
        return registry.retry("fixtureApi");
    }
    
    @Bean
    public Retry playerApiRetry(RetryRegistry registry) {
        return registry.retry("playerApi");
    }
    
    @Bean
    public Retry teamApiRetry(RetryRegistry registry) {
        return registry.retry("teamApi");
    }
}
