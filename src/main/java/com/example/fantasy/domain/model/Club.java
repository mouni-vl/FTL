package com.example.fantasy.domain.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    private Long id;

    private String name;
    private String shortName;
    private String sixLetterName;
    private String threeLetterName;
    private String altThreeLetterName;


    private String nickname;
    private String officialHashtag;
    private String logoUrl;
    private Integer yearFounded;

    private Integer footballingNation;
    private Integer country;
    private Integer city;

    private Integer division;
    private Integer reputation;
    private String likelyFinishingGroup;

    private Integer captain;
    private Integer viceCaptain;

    private Integer ca16;
    private String primaryColor;
    private String secondaryColor;

    private Boolean extinct;
    private Boolean nfe;


    private Stadium stadium;

    // Audit info
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
}

