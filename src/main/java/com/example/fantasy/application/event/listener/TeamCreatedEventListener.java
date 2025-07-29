package com.example.fantasy.application.event.listener;

import com.example.fantasy.application.event.TeamCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Listener for team created events
 */
@Slf4j
@Component
public class TeamCreatedEventListener {
    
    /**
     * Handle team created events
     * @param event the team created event
     */
    @Async
    @EventListener
    public void handleTeamCreatedEvent(TeamCreatedEvent event) {
        log.info("Team created event received: {}", event.getTeam().getName());
        
        // Here you would implement business logic that needs to happen after a team is created
        // For example:
        // 1. Initialize team statistics
        // 2. Add team to default leagues
        // 3. Generate initial team rankings
        // 4. Create default team settings
    }
}
