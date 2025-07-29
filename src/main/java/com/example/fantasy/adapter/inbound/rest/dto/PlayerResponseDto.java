package com.example.fantasy.adapter.inbound.rest.dto;

import com.example.fantasy.domain.model.MainPosition;
import com.example.fantasy.domain.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDto {
    private Long id;
    private Integer fmId;
    private String firstName;
    private String secondName;
    private String fullName;
    private LocalDate dateOfBirth;
    private String cityOfBirth;
    
    // Club information
    private String permanentClub;
    private String basedClub;
    private String loanClub;
    
    // Nationality information
    private String nationality1;
    private String nationality2;
    private String nationality3;
    
    // Stats
    private Integer squadNumber;
    private Boolean nfe;
    private Integer currentAbility;
    private Integer potentialAbility;
    private Integer leftFootSkill;
    private Integer rightFootSkill;
    
    // Positions
    private MainPosition mainPosition;
    private Set<Position> playablePositions;
    
    // Fantasy game stats
    private Double price;
    private Integer totalPoints;
    private String photoUrl;
    private String availabilityStatus;
    
    // Calculated fields for fantasy game
    private Integer form;            // Recent performance trend
    private Double pointsPerGame;    // Average points per game
    private Integer minutesPlayed;   // Total minutes played
    private Integer goalsScored;     // Goals scored this season
    private Integer assists;         // Assists this season
    private Integer cleanSheets;     // Clean sheets (for defenders/goalkeepers)
    private Integer saves;           // Saves (for goalkeepers)
}
