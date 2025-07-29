package com.example.fantasy.application.dto;

import com.example.fantasy.application.dto.light.LightPlayerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer yearFounded;
    private String footballingNation;
    private Integer reputation;
    private String likelyFinishingGroup;
    private LightPlayerDto captain;
    private LightPlayerDto viceCaptain;
    private Integer ca16;
    private String officialHashtag;
    private String nickname;
    private Long stadiumId;
}