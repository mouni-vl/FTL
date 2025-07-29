package com.example.fantasy.adapter.outbound.persistence.entity;

import com.example.fantasy.domain.model.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
//@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
