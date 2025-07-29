package com.example.fantasy.application.event.listener;

import com.example.fantasy.application.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Listener for user created events
 */
@Slf4j
@Component
public class UserCreatedEventListener {
    
    /**
     * Handle user created events
     * @param event the user created event
     */
    @Async
    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        log.info("User created event received: {}", event.getUser().getDisplayName());
        
        // Here you would implement business logic that needs to happen after a user is created
        // For example:
        // 1. Send welcome email
        // 2. Create default settings for the user
        // 3. Add user to default leagues
        // 4. Initialize user statistics
    }
}
