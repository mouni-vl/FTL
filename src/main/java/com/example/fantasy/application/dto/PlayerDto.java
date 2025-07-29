package com.example.fantasy.application.dto;

import com.example.fantasy.domain.model.MainPosition;
import com.example.fantasy.domain.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    private Integer fmId;
    private String firstName;
    private String secondName;
    private String fullName;
    private LocalDate dateOfBirth;
    private String cityOfBirth;
    private String permanentClub;
    private String basedClub;
    private String loanClub;
    private Integer squadNumber;
    private String nationality1;
    private String nationality2;
    private String nationality3;
    private Boolean nfe;
    private Integer currentAbility;
    private Integer potentialAbility;
    private Integer leftFoot;
    private Integer rightFoot;
    private String availabilityStatus;
    private MainPosition mainPosition;
    private Map<String, Integer> positions;
    private Double price;
    private Integer totalPoints;
    private String photoUrl;
    private Boolean active;
}
