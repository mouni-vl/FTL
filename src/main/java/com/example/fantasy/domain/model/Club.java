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
    private Integer fmId;
    private String name;
    private String shortName;
    private String threeLetterName;
    private String logoUrl;
    private Integer yearFounded;

    private String footballingNation;

    private Integer reputation;
    private String likelyFinishingGroup;

    private Player captain;
    private Player viceCaptain;

    private Integer ca16;
    private String officialHashtag;

    private String nickname;

    private Stadium stadium;

    // Audit info
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
}

