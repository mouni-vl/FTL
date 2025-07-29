package com.example.fantasy.adapter.outbound.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

/**
 * Only getters—no setters, Marked @Immutable to prevent any updates at runtime.
 */
@Entity
@Table(name = "position")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class PositionEntity {

    @Id
    @Column(name = "pk_position_id")
    private short positionId;

    @Column(name = "position", nullable = false, unique = true, length = 10)
    private String position;

    @Column(name = "description", nullable = false, length = 50)
    private String description;
}
