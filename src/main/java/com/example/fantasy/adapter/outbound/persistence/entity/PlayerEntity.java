package com.example.fantasy.adapter.outbound.persistence.entity;

import com.example.fantasy.core.persistence.domain.SoftDeletableEntity;
import com.example.fantasy.domain.model.MainPosition;
import com.example.fantasy.domain.model.Position;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * players (1) ←┐
 *               └─ season_stats (many) ←─┐
 *                                         └─ gameweek_stats (many)
 */
@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerEntity extends SoftDeletableEntity {
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "second_name", nullable = false)
    private String secondName;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(name = "date_of_birth", nullable = true)
    private LocalDate dateOfBirth;

    @Column(name = "date_of_birth", nullable = true)
    private Integer yearOfBirth;
    
    @Column(name = "city_of_birth")
    private String cityOfBirth;
    
    @Column(name = "permanent_club")
    private Long permanentClub;
    
    @Column(name = "based_club")
    private Long basedClub;
    
    @Column(name = "loan_club")
    private Long loanClub;
    
    @Column(name = "nationality_1")
    private Long nationality1;
    
    @Column(name = "nationality_2")
    private Long nationality2;
    
    @Column(name = "nationality_3")
    private Long nationality3;
    
    @Column(name = "squad_number")
    private Integer squadNumber;
    
    @Column(name = "nfe")
    private Boolean nfe;
    
    @Column(name = "current_ability")
    private Integer currentAbility;
    
    @Column(name = "potential_ability")
    private Integer potentialAbility;
    
    @Column(name = "left_foot")
    private Integer leftFoot;
    
    @Column(name = "right_foot")
    private Integer rightFoot;
    
    @Column(name = "availability_status", nullable = false)
    private String availabilityStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "main_position")
    private MainPosition mainPosition;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlayerPositionEntity positions;

}
