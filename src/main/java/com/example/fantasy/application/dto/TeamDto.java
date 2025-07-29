package com.example.fantasy.application.dto;

import com.example.fantasy.core.persistence.domain.BaseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeamDto extends BaseDto {
    @NotBlank(message = "Team name is required")
    private String name;
    
    @NotNull(message = "Owner ID is required")
    private Long ownerId;
    
    @NotNull(message = "League ID is required")
    private Long leagueId;
    
    private int points;
    
    @Positive(message = "Budget must be positive")
    private double budget;
    
    private List<Long> playerIds;
}
