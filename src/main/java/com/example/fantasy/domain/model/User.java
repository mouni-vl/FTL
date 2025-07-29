package com.example.fantasy.domain.model;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User {
    private String email;
    private String passwordHash;
    private String displayName;
    private Role role;           // enum: USER, ADMIN
}
