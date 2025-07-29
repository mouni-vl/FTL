package com.example.fantasy.adapter.outbound.persistence.entity;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "club")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ClubEntity extends BaseEntity {
    @Column(name = "fm_id", nullable = false, unique = true)
    private Integer fmId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "short_name", length = 50)
    private String shortName;

    @Column(name = "three_letter_name", length = 10)
    private String threeLetterName;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "year_founded")
    private Integer yearFounded;

    @Column(name = "footballing_nation", length = 50)
    private String footballingNation;

    @Column(name = "reputation")
    private Integer reputation;

    @Column(name = "likely_finishing_group", length = 50)
    private String likelyFinishingGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "captain_id")
    private PlayerEntity captain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vice_captain_id")
    private PlayerEntity viceCaptain;

    @Column(name = "ca16")
    private Integer ca16;

    @Column(name = "official_hashtag", length = 50)
    private String officialHashtag;

    @Column(name = "nickname", length = 100)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    private StadiumEntity stadium;
}