package com.example.fantasy.batch.csvRecord;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubCsvRecord {

    private Long id;
    private Long fmId;
    private String name;
    private String shortName;
    private String sixLetterName;
    private String threeLetterName;
    private String altThreeLetterName;

    private Integer footballingNationId;
    private String footballingNationName;
    private Integer countryId;
    private String countryName;
    private Integer cityId;
    private String cityName;

    private Integer divisionId;
    private String divisionName;

    private Integer reputation;
    private String likelyFinishingGroup;

    private Integer captainId;
    private String captainName;
    private Integer viceCaptainId;
    private String viceCaptainName;

    private BigDecimal ca16;
    private Boolean extinct;
    private Boolean nfe;
    private String officialHashtag;
    private Integer yearFounded;

    private String nickname;
    private String primaryColor;
    private String secondaryColor;

    private Long stadiumId;
    //private Long stadiumId;
    private String stadiumName;
    private Integer maxAttendance;

}
