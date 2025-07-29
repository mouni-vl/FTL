package com.example.fantasy.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fixture {
    private Long id;
    private String externalId;
    private Long seasonId;
    private String homeClub;
    private String awayClub;
    private Instant kickoffAt;
    private String status;
    private Integer gameweekNumber;
}
