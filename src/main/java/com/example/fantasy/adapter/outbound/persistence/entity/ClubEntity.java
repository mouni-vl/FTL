package com.example.fantasy.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * JPA entity representing a football club
 */
@Entity
@Table(name = "club")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"players"})
public class ClubEntity {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "three_letter_name")
    private String threeLetterName;

    @Column(name = "footballing_nation")
    private Integer footballingNation;

    @Column
    private Integer reputation;

    @Column(name = "likely_finishing_group")
    private String likelyFinishingGroup;

    @Column
    private Integer ca16;

    @Column(name = "official_hashtag")
    private String officialHashtag;

    @Column(name = "year_founded")
    private Integer yearFounded;

    @Column
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    private StadiumEntity stadium;

    @OneToMany(mappedBy = "permanentClub", cascade = CascadeType.REMOVE)
    private Set<PlayerEntity> players = new HashSet<>();

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;
}
