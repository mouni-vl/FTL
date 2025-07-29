package com.example.fantasy.application.event;

import com.example.fantasy.domain.model.Team;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Publisher for team-related events
 */
@Component
public class TeamEventPublisher extends AbstractEventPublisher {
    
    public TeamEventPublisher(ApplicationEventPublisher eventPublisher) {
        super(eventPublisher);
    }
    
    /**
     * Publish a team created event
     * @param team the created team
     */
    public void publishTeamCreatedEvent(Team team) {
        publishEvent(new TeamCreatedEvent(team));
    }
    
    /**
     * Publish a team updated event
     * @param team the updated team
     */
    public void publishTeamUpdatedEvent(Team team) {
        // In a real application, you would create and publish a TeamUpdatedEvent
        // For now, we're just logging the event
        publishEvent("Team updated: " + team.getName());
    }
    
    /**
     * Publish a team deleted event
     * @param teamId the ID of the deleted team
     */
    public void publishTeamDeletedEvent(Long teamId) {
        // In a real application, you would create and publish a TeamDeletedEvent
        // For now, we're just logging the event
        publishEvent("Team deleted: " + teamId);
    }
}
