package com.example.fantasy.domain.model;

import lombok.Getter;

@Getter
public class GameweekStats {
    private final Integer playerId;  // for reference
    private final String season;     // e.g. "2024/25"
    private final Integer round;     // gameweek number
    private final Integer minutes;
    private final Integer goalsScored;
    private final Integer assists;
    private final Integer cleanSheets;
    private final Integer bonus;
    // … other fields (bps, epThis, epNext, etc.)

    public GameweekStats(Integer playerId, String season, Integer round, Integer minutes,
                         Integer goalsScored, Integer assists, Integer cleanSheets, Integer bonus) {
        this.playerId = playerId;
        this.season = season;
        this.round = round;
        this.minutes = minutes;
        this.goalsScored = goalsScored;
        this.assists = assists;
        this.cleanSheets = cleanSheets;
        this.bonus = bonus;
    }
}