package com.example.fantasy.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Stadium domain model representing a football stadium
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {
    private Long id;
    private String name;
    private Integer capacity;
}
