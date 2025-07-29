package com.example.fantasy.application.event;

import com.example.fantasy.domain.model.Team;
import lombok.Getter;

/**
 * Event triggered when a team is created
 */
@Getter
public class TeamCreatedEvent extends DomainEvent {
    
    private final Team team;
    
    public TeamCreatedEvent(Team team) {
        super();
        this.team = team;
    }
}
