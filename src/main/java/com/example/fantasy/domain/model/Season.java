package com.example.fantasy.domain.model;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Season extends BaseEntity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private List<League> leagues;
}
