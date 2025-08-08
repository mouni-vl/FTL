package com.example.fantasy.batch.csvRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCsvRecord {
    private String config;

    private Long fmId;
    private String firstName;
    private String secondName;
    private String fullName;

    private Long permanentClubId;
    private String permanentClubName;
    private Long basedClubId;
    private String basedClubName;
    private Long loanClubId;
    private String loanClubName;

    private String dateOfBirth;
    private String yearOfBirth;

    private Long nationalityId;
    private String nationalityName;
    private Long secondNationalityId;
    private String secondNationalityName;
    private Long thirdNationalityId;
    private String thirdNationalityName;

    private Integer cityOfBirthId;
    private String cityOfBirth;

    private Integer gk;
    private Integer dl;
    private Integer dc;
    private Integer dr;
    private Integer wbl;
    private Integer wbr;
    private Integer dm;
    private Integer mc;
    private Integer ml;
    private Integer mr;
    private Integer aml;
    private Integer amc;
    private Integer amr;
    private Integer sc;

    private Integer currentAbility;
    private Integer potentialAbility;

    private Integer leftFoot;
    private Integer rightFoot;
    private Integer nfe;
    private Integer squadNumber;
}