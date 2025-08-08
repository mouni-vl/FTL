package com.example.fantasy.batch.reader.club;

import com.example.fantasy.batch.csvRecord.ClubCsvRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import static com.example.fantasy.shared.utils.NumberUtils.safeReadLong;

@Slf4j
public class ClubCsvRecordFieldSetMapper implements FieldSetMapper<ClubCsvRecord> {

    @Override
    public ClubCsvRecord mapFieldSet(FieldSet fieldSet) throws BindException {
        if (fieldSet == null) {
            log.debug("FieldSet is null, skipping");
            return null;
        }

        ClubCsvRecord record = new ClubCsvRecord();

        try {
        record.setFmId(safeReadLong(fieldSet, "FM ID"));
        record.setName(fieldSet.readString("Name"));
        record.setShortName(fieldSet.readString("Short Name"));
        record.setSixLetterName(fieldSet.readString("6 Letter Name"));
        record.setThreeLetterName(fieldSet.readString("3 Letter Name"));
        record.setAltThreeLetterName(fieldSet.readString("Alt 3 Letter Name"));

        record.setFootballingNationId(fieldSet.readInt("Footballing Nation ID"));
        record.setFootballingNationName(fieldSet.readString("Footballing Nation Name"));
        record.setCountryId(fieldSet.readInt("Country ID"));
        record.setCountryName(fieldSet.readString("Country Name"));
        record.setCityId(fieldSet.readInt("City ID"));
        record.setCityName(fieldSet.readString("City Name"));

        record.setDivisionId(fieldSet.readInt("Division ID"));
        record.setDivisionName(fieldSet.readString("Division Name"));

        record.setReputation(fieldSet.readInt("Reputation"));
        record.setLikelyFinishingGroup(fieldSet.readString("Likely Finishing Group"));

        record.setCaptainId(fieldSet.readInt("Captain ID"));
        record.setCaptainName(fieldSet.readString("Captain Name"));
        record.setViceCaptainId(fieldSet.readInt("Vice Captain ID"));
        record.setViceCaptainName(fieldSet.readString("Vice Captain Name"));

        record.setCa16(fieldSet.readInt("CA16"));
        record.setExtinct(fieldSet.readBoolean("Extinct"));
        record.setNfe(fieldSet.readBoolean("NFE"));
        record.setOfficialHashtag(fieldSet.readString("Official Hashtag #"));
        record.setYearFounded(fieldSet.readInt("Year Founded"));
        record.setNickname(fieldSet.readString("First Nickname"));
        record.setPrimaryColor(fieldSet.readString("Titlebar FG Colour"));
        record.setSecondaryColor(fieldSet.readString("Titlebar BG Colour"));

        record.setStadiumFmId(safeReadLong(fieldSet, "StadiumFM ID"));
        record.setStadiumName(fieldSet.readString("Stadium Name"));
        record.setMaxAttendance(fieldSet.readInt("Max Attendance"));
        } catch (Exception e) {
            log.error("Failed to map FieldSet to PlayerCsvRecord: {}", fieldSet, e);
            throw new BindException(fieldSet, "PlayerCsvRecord");
        }
        return record;
    }
}
