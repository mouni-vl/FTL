package com.example.fantasy.domain.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stadium {
    private Long id;
    private String name;
    private Integer capacity;
}
