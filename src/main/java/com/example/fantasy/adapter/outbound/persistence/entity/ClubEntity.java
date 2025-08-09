package com.example.fantasy.adapter.outbound.persistence.entity;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "club")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class ClubEntity extends BaseEntity {
    @Column(name = "fm_id", nullable = false, unique = true)
    private Long fmId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "short_name", length = 50)
    private String shortName;

    @Column(name = "six_letter_name", length = 10)
    private String sixLetterName;

    @Column(name = "three_letter_name", length = 10)
    private String threeLetterName;

    @Column(name = "alt_three_letter_name", length = 10)
    private String altThreeLetterName;

    @Column(name = "nickname", length = 100)
    private String nickname;

    @Column(name = "official_hashtag", length = 50)
    private String officialHashtag;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "year_founded")
    private Integer yearFounded;

    @Column(name = "footballing_nation")
    private Integer footballingNation;

    @Column(name = "country")
    private Integer country;

    @Column(name = "city")
    private Integer city;

    @Column(name = "division_id")
    private Integer division;

    @Column(name = "reputation")
    private Integer reputation;

    @Column(name = "likely_finishing_group", length = 50)
    private String likelyFinishingGroup;

    @Column(name = "captain_id")
    private Integer captain;

    @Column(name = "vice_captain_id")
    private Integer viceCaptain;

    @Column(name = "ca16", precision = 5, scale = 2)
    private BigDecimal ca16;

    @Column(name = "primary_color", length = 20)
    private String primaryColor;

    @Column(name = "secondary_color", length = 20)
    private String secondaryColor;

    @Column(name = "extinct")
    private Boolean extinct;

    @Column(name = "nfe")
    private Boolean nfe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id")
    private StadiumEntity stadium;
}

