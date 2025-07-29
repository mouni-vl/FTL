package com.example.fantasy.batch.csvRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCsvRecord {
    private Integer fmId;
    private String firstName;
    private String secondName;
    private String fullName;
    private String dateOfBirth;
    private String cityOfBirth;
    private String permanentClub;
    private String basedClub;
    private String loanClub;
    private String squadNumber;
    private String nationality1;
    private String nationality2;
    private String nationality3;
    private String nfe;
    private String currentAbility;
    private String potentialAbility;
    private String leftFoot;
    private String rightFoot;
    private String availabilityStatus;
    private String mainPosition;
    private String positions;
    private String price;
    private String photoUrl;
}
