package com.example.fantasy.adapter.outbound.persistence.entity;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stadium")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StadiumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity")
    private Integer capacity;
    
    @OneToMany(mappedBy = "stadium")
    private Set<ClubEntity> clubs = new HashSet<>();
}
