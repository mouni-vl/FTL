package com.example.fantasy.adapter.outbound.integration.teamapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalTeamDto {
    @JsonProperty("team_id")
    private String id;

    @JsonProperty("team_name")
    private String name;

    @JsonProperty("club_id")
    private String clubId;
}
