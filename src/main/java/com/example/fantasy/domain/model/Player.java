package com.example.fantasy.domain.model;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

/**
 * Mutable domain model for a player.
 * This class represents a player in the fantasy game, including personal information,
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Long id;

    private Integer fmId;
    private String firstName;
    private String secondName;
    private String fullName;
    private String photoUrl;
    private LocalDate dateOfBirth;
    private String cityOfBirth;

    // Club information
    private Long permanentClub;
    private Long basedClub;
    private Long loanClub;
    private Integer squadNumber;

    // Nationality information
    private Long nationality1;
    private Long nationality2;
    private Long nationality3;

    // Stats
    private Boolean nfe;
    private Integer currentAbility;
    private Integer potentialAbility;
    private Integer leftFoot;
    private Integer rightFoot;

    // Game status
    private String availabilityStatus;

    // Positions
    private MainPosition mainPosition;
    private Map<String, Integer> positions;

    // Fantasy game stats
    private Double price;
    private Integer totalPoints;
    private Boolean active;

    private Boolean deleted;

    // Audit info
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
}
