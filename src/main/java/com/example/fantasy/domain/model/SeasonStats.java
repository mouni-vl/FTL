package com.example.fantasy.domain.model;

import java.time.Year;

public class SeasonStats {
    private final Integer playerId;   // FPL’s element ID
    private final String season;      // e.g. "2024/25"
    private final Short teamId;
    private final Integer totalPoints;
    private final Integer minutes;
    private final Integer goalsScored;
    private final Integer assists;
    // … other fields (cleanSheets, bonus, etc.)

    public SeasonStats(Integer playerId, String season, Short teamId, Integer totalPoints,
                       Integer minutes, Integer goalsScored, Integer assists) {
        this.playerId = playerId;
        this.season = season;
        this.teamId = teamId;
        this.totalPoints = totalPoints;
        this.minutes = minutes;
        this.goalsScored = goalsScored;
        this.assists = assists;
    }

    // Constructors, getters, any domain logic (e.g. compute minutesPerGame)
    // No JPA annotations here.
}