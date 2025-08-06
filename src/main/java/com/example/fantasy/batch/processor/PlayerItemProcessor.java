package com.example.fantasy.batch.processor;

import com.example.fantasy.batch.csvRecord.PlayerCsvRecord;
import com.example.fantasy.domain.model.MainPosition;
import com.example.fantasy.domain.model.Player;
import com.example.fantasy.domain.model.PlayerPosition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerItemProcessor implements ItemProcessor<PlayerCsvRecord, Player> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String BATCH = "Import-Batch";

    @Override
    public Player process(PlayerCsvRecord record) throws Exception {
        // Validate mandatory fields
        if (!isValidRecord(record)) {
            log.warn("Skipping invalid record: {}", record);
            return null; // Returning null will skip this item
        }

        try {
            Player player = Player.builder()
                    .fmId(record.getFmId())
                    .firstName(record.getFirstName())
                    .secondName(record.getSecondName())
                    .fullName(StringUtils.hasText(record.getFullName()) ? 
                            record.getFullName() : 
                            record.getFirstName() + " " + record.getSecondName())

                    .dateOfBirth(parseDate(record.getDateOfBirth()))
                    .cityOfBirth(record.getCityOfBirth())

                    .permanentClub(record.getPermanentClubId())
                    .basedClub(record.getBasedClubId())
                    .loanClub(record.getLoanClubId())
                    .squadNumber(record.getSquadNumber())

                    .nationality1(record.getNationalityId())
                    .nationality2(record.getSecondNationalityId())
                    .nationality3(record.getThirdNationalityId())

                    .nfe(parseBoolean(record.getNfe()))

                    .currentAbility(record.getCurrentAbility())
                    .potentialAbility(record.getPotentialAbility())

                    .leftFoot(record.getLeftFoot())
                    .rightFoot(record.getRightFoot())

                    //.mainPosition(parseMainPosition(record.getMainPosition()))
                    .positions(extractPositions(record))
                    .mainPosition(determineMainPosition(extractPositions(record)))
                    .price(0.0)
                    .totalPoints(0)

                    .active(true)
                    .deleted(false)

                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .createdBy(BATCH)
                    .updatedBy(BATCH)
                    .build();

            log.debug("Processed player: {}", player.getFullName());
            return player;

        } catch (Exception e) {
            log.error("Error processing record: {}", record, e);
            throw e;
        }
    }

    private boolean isValidRecord(PlayerCsvRecord record) {
        if (record.getFmId() == null) {
            log.warn("Missing fmId in record");
            return false;
        }

        if (!StringUtils.hasText(record.getFirstName()) || !StringUtils.hasText(record.getSecondName())) {
            log.warn("Missing name fields in record with fmId: {}", record.getFmId());
            return false;
        }

        return true;
    }

    private LocalDate parseDate(String dateString) {
        if (!StringUtils.hasText(dateString)) {
            return null;
        }
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            log.warn("Invalid date format: {}", dateString);
            return null;
        }
    }

    private Integer parseInteger(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double parseDouble(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Boolean parseBoolean(Integer value) {
        if (value == null) {
            return false;
        }
        return value == 1;
    }

    private Map<String, Integer> extractPositions(PlayerCsvRecord record) {
        PlayerPosition position = PlayerPosition.builder()
                .gk(record.getGk())
                .dl(record.getDl())
                .dc(record.getDc())
                .dr(record.getDr())
                .wbl(record.getWbl())
                .wbr(record.getWbr())
                .dm(record.getDm())
                .mc(record.getMc())
                .ml(record.getMl())
                .mr(record.getMr())
                .aml(record.getAml())
                .amc(record.getAmc())
                .amr(record.getAmr())
                .sc(record.getSc())
                .build();

        return position.toMap();
    }

    //refactor later
    private MainPosition determineMainPosition(Map<String, Integer> positions) {
        return positions.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> MainPosition.valueOf(entry.getKey()))
                .orElse(MainPosition.UNKNOWN);
    }

}
