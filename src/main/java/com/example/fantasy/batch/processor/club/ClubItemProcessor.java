package com.example.fantasy.batch.processor.club;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import com.example.fantasy.adapter.outbound.persistence.entity.StadiumEntity;
import com.example.fantasy.adapter.outbound.persistence.mapper.StadiumEntityMapper;
import com.example.fantasy.application.service.StadiumServiceImpl;
import com.example.fantasy.batch.csvRecord.ClubCsvRecord;
import com.example.fantasy.domain.model.Stadium;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import static com.example.fantasy.shared.utils.DateTimeUtils.nowUtc;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClubItemProcessor implements ItemProcessor<ClubCsvRecord, ClubEntity> {

    private static final String BATCH = "Import-Batch";
    private final StadiumServiceImpl stadiumService;
    private final StadiumEntityMapper stadiumMapper;

    @Override
    public ClubEntity process(ClubCsvRecord record) throws Exception {
        if (!isValidRecord(record)) {
            log.warn("Skipping invalid club record: {}", record);
            return null;
        }

        try {
            // Process Stadium using the service
            Stadium domainStadium = stadiumService.findOrCreate(
                    record.getStadiumId(),
                    record.getStadiumName(),
                    record.getMaxAttendance());

            // Convert domain stadium to entity
            StadiumEntity stadium = stadiumMapper.toSource(domainStadium);

            ClubEntity club = new ClubEntity();
            club.setId(record.getId());
            club.setFmId(record.getFmId());
            club.setName(record.getName());
            club.setShortName(record.getShortName());
            club.setThreeLetterName(record.getThreeLetterName());
            club.setFootballingNation(record.getFootballingNationId());
            club.setReputation(record.getReputation());
            club.setLikelyFinishingGroup(record.getLikelyFinishingGroup());
            club.setCa16(record.getCa16());
            club.setOfficialHashtag(record.getOfficialHashtag());
            club.setYearFounded(record.getYearFounded());
            club.setNickname(record.getNickname());
            club.setStadium(stadium);

            club.setCreatedAt(nowUtc());
            club.setUpdatedAt(nowUtc());
            club.setCreatedBy(BATCH);
            club.setUpdatedBy(BATCH);

            log.debug("Processed club: {} (Stadium: {})", club.getName(),
                    stadium != null ? stadium.getName() : "None");

            return club;

        } catch (Exception e) {
            log.error("Error processing club record: {}", record, e);
            throw e;
        }
    }

    private boolean isValidRecord(ClubCsvRecord record) {
        if (record.getFmId() == null || record.getId() == null || !StringUtils.hasText(record.getName())) {
            log.warn("Invalid record: Missing ID or name");
            return false;
        }
        return true;
    }

}