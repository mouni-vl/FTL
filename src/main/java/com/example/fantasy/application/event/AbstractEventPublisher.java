package com.example.fantasy.application.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Abstract event publisher for domain events
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractEventPublisher {
    
    private final ApplicationEventPublisher eventPublisher;
    
    /**
     * Publish an event
     * @param event the event to publish
     * @param <T> the type of the event
     */
    protected <T> void publishEvent(T event) {
        log.debug("Publishing event: {}", event);
        eventPublisher.publishEvent(event);
    }
}
