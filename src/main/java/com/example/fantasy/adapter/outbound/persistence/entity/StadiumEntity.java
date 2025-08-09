package com.example.fantasy.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * JPA entity representing a Stadium
 */
@Entity
@Table(name = "stadium")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class StadiumEntity{

    @Id
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private Integer capacity;

    @OneToMany(mappedBy = "stadium", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<ClubEntity> clubs = new HashSet<>();
}
