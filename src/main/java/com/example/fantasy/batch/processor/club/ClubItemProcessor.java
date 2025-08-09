package com.example.fantasy.batch.processor.club;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import com.example.fantasy.adapter.outbound.persistence.entity.StadiumEntity;
import com.example.fantasy.adapter.outbound.persistence.mapper.StadiumEntityMapper;
import com.example.fantasy.adapter.outbound.persistence.repository.ClubJpaRepository;
import com.example.fantasy.application.service.StadiumServiceImpl;
import com.example.fantasy.batch.csvRecord.ClubCsvRecord;
import com.example.fantasy.domain.model.Stadium;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.fantasy.shared.utils.DateTimeUtils.nowUtc;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClubItemProcessor implements ItemProcessor<ClubCsvRecord, ClubEntity> {

    private static final String BATCH = "Import-Batch";
    private final StadiumServiceImpl stadiumService;
    private final StadiumEntityMapper stadiumMapper;
    private final ClubJpaRepository clubRepository;

    @Override
    public ClubEntity process(ClubCsvRecord record) throws Exception {
        if (!isValidRecord(record)) {
            log.warn("Skipping invalid club record: {}", record);
            return null;
        }

        try {
            // Check if club already exists
            boolean isUpdate = false;
            ClubEntity club = null;

            if (record.getId() != null) {
                Optional<ClubEntity> existingClub = clubRepository.findById(record.getId());
                if (existingClub.isPresent()) {
                    club = existingClub.get();
                    isUpdate = true;
                    log.debug("Found existing club with ID {}: {}", record.getId(), club.getName());
                }
            }

            if (club == null) {
                club = new ClubEntity();
                club.setId(record.getId());
                club.setCreatedAt(nowUtc());
                club.setCreatedBy(BATCH);
                log.debug("Creating new club with ID {}: {}", record.getId(), record.getName());
            }

            // Process Stadium using the service
            Stadium domainStadium = stadiumService.findOrCreate(
                    record.getStadiumId(),
                    record.getStadiumName(),
                    record.getMaxAttendance());

            // Convert domain stadium to entity
            StadiumEntity stadium = stadiumMapper.toSource(domainStadium);

            club.setFmId(record.getFmId());
            club.setName(record.getName());
            club.setShortName(record.getShortName());
            club.setSixLetterName(record.getSixLetterName());
            club.setThreeLetterName(record.getThreeLetterName());
            club.setAltThreeLetterName(record.getAltThreeLetterName());
            club.setFootballingNation(record.getFootballingNationId());
            club.setCountry(record.getCountryId());
            club.setCity(record.getCityId());
            club.setDivision(record.getDivisionId());
            club.setReputation(record.getReputation());
            club.setCaptain(record.getCaptainId());
            club.setViceCaptain(record.getViceCaptainId());
            club.setLikelyFinishingGroup(record.getLikelyFinishingGroup());
            club.setCa16(BigDecimal.valueOf(record.getCa16() != null ? record.getCa16().intValue() : null));
            club.setOfficialHashtag(record.getOfficialHashtag());
            club.setYearFounded(record.getYearFounded());
            club.setNickname(record.getNickname());
            club.setStadium(stadium);
            club.setNfe(record.getNfe());
            club.setExtinct(record.getExtinct());
            club.setUpdatedAt(nowUtc());
            club.setUpdatedBy(BATCH);

            if (isUpdate) {
                log.info("Updated existing club: {} (ID: {})", club.getName(), club.getId());
            } else {
                log.info("Created new club: {} (ID: {})", club.getName(), club.getId());
            }

            return club;

        } catch (Exception e) {
            log.error("Error processing club record: {}", record, e);
            throw e;
        }
    }

    private boolean isValidRecord(ClubCsvRecord record) {
        if (record.getId() == null || !StringUtils.hasText(record.getName())) {
            log.warn("Invalid record: Missing ID or name");
            return false;
        }
        return true;
    }

}