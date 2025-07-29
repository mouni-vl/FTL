package com.example.fantasy.adapter.outbound.integration.fixtureapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class ExternalFixtureDto {
    @JsonProperty("fixture_id")
    private String id;

    @JsonProperty("season_id")
    private Long seasonId;

    @JsonProperty("home_team_id")
    private String homeClub;

    @JsonProperty("away_team_id")
    private String awayClub;

    @JsonProperty("kickoff_at")
    private Instant kickoffAt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("gameweek")
    private Integer gameweekNumber;
}
