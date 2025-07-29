package com.example.fantasy.domain.model;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class League extends BaseEntity {
    private String name;
    private String description;
    private User owner;
    private boolean isPublic;
    private int maxTeams;
    private List<Team> teams;
}
