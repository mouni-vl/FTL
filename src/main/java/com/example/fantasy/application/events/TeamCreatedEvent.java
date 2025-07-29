package com.example.fantasy.application.events;

import com.example.fantasy.domain.model.Team;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TeamCreatedEvent extends ApplicationEvent {
    
    private final Team team;
    
    public TeamCreatedEvent(Object source, Team team) {
        super(source);
        this.team = team;
    }
}
