package com.example.fantasy.application.dto;

import com.example.fantasy.application.dto.light.LightPlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDto {
    private Long id;
    private Integer fmId;

    private String name;
    private String shortName;
    private String threeLetterName;
    private String sixLetterName;
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

    private StadiumDto stadium;

    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
}
