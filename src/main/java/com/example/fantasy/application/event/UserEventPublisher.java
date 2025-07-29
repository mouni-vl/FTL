package com.example.fantasy.application.event;

import com.example.fantasy.domain.model.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Publisher for user-related events
 */
@Component
public class UserEventPublisher extends AbstractEventPublisher {
    
    public UserEventPublisher(ApplicationEventPublisher eventPublisher) {
        super(eventPublisher);
    }
    
    /**
     * Publish a user created event
     * @param user the created user
     */
    public void publishUserCreatedEvent(User user) {
        publishEvent(new UserCreatedEvent(user));
    }
    
    /**
     * Publish a user updated event
     * @param user the updated user
     */
    public void publishUserUpdatedEvent(User user) {
        // In a real application, you would create and publish a UserUpdatedEvent
        // For now, we're just logging the event
        publishEvent("User updated: " + user.getDisplayName());
    }
    
    /**
     * Publish a user deleted event
     * @param userId the ID of the deleted user
     */
    public void publishUserDeletedEvent(Long userId) {
        // In a real application, you would create and publish a UserDeletedEvent
        // For now, we're just logging the event
        publishEvent("User deleted: " + userId);
    }
}
