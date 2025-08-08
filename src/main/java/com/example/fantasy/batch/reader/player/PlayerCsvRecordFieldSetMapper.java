package com.example.fantasy.batch.reader.player;

import com.example.fantasy.batch.csvRecord.PlayerCsvRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import static com.example.fantasy.shared.utils.NumberUtils.safeReadInt;
import static com.example.fantasy.shared.utils.NumberUtils.safeReadLong;

@Slf4j
public class PlayerCsvRecordFieldSetMapper implements FieldSetMapper<PlayerCsvRecord> {

    @Override
    public PlayerCsvRecord mapFieldSet(FieldSet fs) throws BindException {
        if (fs == null) {
            log.debug("FieldSet is null, skipping");
            return null;
        }

        try {
            return PlayerCsvRecord.builder()
                    .config(fs.readString("config"))
                    .fmId(safeReadLong(fs,"fmId"))
                    .firstName(fs.readString("firstName"))
                    .secondName(fs.readString("secondName"))
                    .fullName(fs.readString("fullName"))
                    .permanentClubId(safeReadLong(fs,"permanentClubId"))
                    .permanentClubName(fs.readString("permanentClubName"))
                    .basedClubId(safeReadLong(fs,"basedClubId"))
                    .basedClubName(fs.readString("basedClubName"))
                    .loanClubId(safeReadLong(fs,"loanClubId"))
                    .loanClubName(fs.readString("loanClubName"))
                    .dateOfBirth(fs.readString("dateOfBirth"))
                    .yearOfBirth(fs.readString("yearOfBirth"))
                    .nationalityId(safeReadLong(fs,"nationalityId"))
                    .nationalityName(fs.readString("nationalityName"))
                    .secondNationalityId(safeReadLong(fs,"secondNationalityId"))
                    .secondNationalityName(fs.readString("secondNationalityName"))
                    .thirdNationalityId(safeReadLong(fs,"thirdNationalityId"))
                    .thirdNationalityName(fs.readString("thirdNationalityName"))
                    .cityOfBirthId(safeReadInt(fs,"cityOfBirthId"))
                    .cityOfBirth(fs.readString("cityOfBirth"))
                    .gk(safeReadInt(fs,"gk"))
                    .dl(safeReadInt(fs,"dl"))
                    .dc(safeReadInt(fs,"dc"))
                    .dr(safeReadInt(fs,"dr"))
                    .wbl(safeReadInt(fs,"wbl"))
                    .wbr(safeReadInt(fs,"wbr"))
                    .dm(safeReadInt(fs,"dm"))
                    .mc(safeReadInt(fs,"mc"))
                    .ml(safeReadInt(fs,"ml"))
                    .mr(safeReadInt(fs,"mr"))
                    .aml(safeReadInt(fs,"aml"))
                    .amc(safeReadInt(fs,"amc"))
                    .amr(safeReadInt(fs,"amr"))
                    .sc(safeReadInt(fs,"sc"))
                    .currentAbility(safeReadInt(fs,"currentAbility"))
                    .potentialAbility(safeReadInt(fs,"potentialAbility"))
                    .leftFoot(safeReadInt(fs,"leftFoot"))
                    .rightFoot(safeReadInt(fs,"rightFoot"))
                    .nfe(safeReadInt(fs,"nfe"))
                    .squadNumber(safeReadInt(fs,"squadNumber"))
                    .build();

        } catch (Exception e) {
            log.error("Failed to map FieldSet to PlayerCsvRecord: {}", fs, e);
            throw new BindException(fs, "PlayerCsvRecord");
        }
    }
}