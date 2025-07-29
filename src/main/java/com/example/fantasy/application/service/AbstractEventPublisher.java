package com.example.fantasy.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public abstract class AbstractEventPublisher<T> {
    
    protected final ApplicationEventPublisher eventPublisher;
    
    /**
     * Publishes a domain event
     * @param event the event to publish
     */
    protected void publishDomainEvent(Object event) {
        eventPublisher.publishEvent(event);
    }
}
