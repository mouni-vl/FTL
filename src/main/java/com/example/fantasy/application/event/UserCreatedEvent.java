package com.example.fantasy.application.event;

import com.example.fantasy.domain.model.User;
import lombok.Getter;

/**
 * Event triggered when a user is created
 */
@Getter
public class UserCreatedEvent extends DomainEvent {
    
    private final User user;
    
    public UserCreatedEvent(User user) {
        super();
        this.user = user;
    }
}
