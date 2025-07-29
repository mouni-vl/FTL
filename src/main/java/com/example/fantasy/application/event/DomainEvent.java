package com.example.fantasy.application.event;

import lombok.Getter;

import java.time.Instant;

/**
 * Base class for all domain events
 */
@Getter
public abstract class DomainEvent {
    
    private final String eventId;
    private final Instant timestamp;
    
    protected DomainEvent() {
        this.eventId = java.util.UUID.randomUUID().toString();
        this.timestamp = Instant.now();
    }
}
