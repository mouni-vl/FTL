package com.example.fantasy.adapter.inbound.rest.dto;

import com.example.fantasy.domain.model.MainPosition;
import com.example.fantasy.domain.model.Position;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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
public class PlayerCreateDto {
    @NotNull(message = "FM ID is required")
    private Integer fmId;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Second name is required")
    private String secondName;
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @NotNull(message = "Date of birth is required")
    @PastOrPresent(message = "Date of birth must be in the past or present")
    private LocalDate dateOfBirth;
    
    private String cityOfBirth;
    
    private String permanentClub;
    private String basedClub;
    private String loanClub;
    
    private String nationality1;
    private String nationality2;
    private String nationality3;
    
    private Integer squadNumber;
    private Boolean nfe;
    
    @PositiveOrZero(message = "Current ability cannot be negative")
    private Integer currentAbility;
    
    @PositiveOrZero(message = "Potential ability cannot be negative")
    private Integer potentialAbility;
    
    @PositiveOrZero(message = "Left foot skill cannot be negative")
    private Integer leftFootSkill;
    
    @PositiveOrZero(message = "Right foot skill cannot be negative")
    private Integer rightFootSkill;
    
    @NotNull(message = "Availability status is required")
    private String availabilityStatus;
    
    @NotNull(message = "Main position is required")
    private MainPosition mainPosition;
    
    private Set<Position> playablePositions;
    
    @PositiveOrZero(message = "Price cannot be negative")
    private Double price;
    
    @PositiveOrZero(message = "Total points cannot be negative")
    private Integer totalPoints;
    
    private String photoUrl;
    private Boolean active;
}
