package com.example.fantasy.adapter.outbound.integration.playerapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalPlayerDto {
    @JsonProperty("player_id")
    private String id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("position")
    private String position;

    @JsonProperty("club_id")
    private String clubId;

    @JsonProperty("cost")
    private Double cost;

    @JsonProperty("photo_url")
    private String photoUrl;

    @JsonProperty("active")
    private boolean active;
}
