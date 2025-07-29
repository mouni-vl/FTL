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
public class Team extends BaseEntity {
    private String externalId;
    private String name;
    private User owner;
    private League league;
    private int points;
    private double budget;
    private String clubId;
    private List<Player> players;
}
