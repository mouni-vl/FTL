package com.example.fantasy.batch.processor;

import com.example.fantasy.batch.csvRecord.PlayerCsvRecord;
import com.example.fantasy.domain.model.MainPosition;
import com.example.fantasy.domain.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class PlayerItemProcessor implements ItemProcessor<PlayerCsvRecord, Player> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Set<String> VALID_POSITIONS = Arrays.stream(MainPosition.values())
            .map(MainPosition::name)
            .collect(Collectors.toSet());

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
                    .permanentClub(record.getPermanentClub())
                    .basedClub(record.getBasedClub())
                    .loanClub(record.getLoanClub())
                    .squadNumber(parseInteger(record.getSquadNumber()))
                    .nationality1(record.getNationality1())
                    .nationality2(record.getNationality2())
                    .nationality3(record.getNationality3())
                    .nfe(parseBoolean(record.getNfe()))
                    .currentAbility(parseInteger(record.getCurrentAbility()))
                    .potentialAbility(parseInteger(record.getPotentialAbility()))
                    .leftFoot(parseInteger(record.getLeftFoot()))
                    .rightFoot(parseInteger(record.getRightFoot()))
                    .availabilityStatus(StringUtils.hasText(record.getAvailabilityStatus()) ? 
                            record.getAvailabilityStatus() : "Available")
                    .mainPosition(parseMainPosition(record.getMainPosition()))
                    .positions(parsePositions(record.getPositions()))
                    .price(parseDouble(record.getPrice()))
                    .photoUrl(record.getPhotoUrl())
                    .totalPoints(0)
                    .active(true)
                    .deleted(false)
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

        if (!StringUtils.hasText(record.getMainPosition()) || 
                !isValidPosition(record.getMainPosition())) {
            log.warn("Invalid main position in record with fmId: {}", record.getFmId());
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

    private Boolean parseBoolean(String value) {
        if (!StringUtils.hasText(value)) {
            return false;
        }
        return value.trim().equalsIgnoreCase("true") || 
               value.trim().equalsIgnoreCase("yes") || 
               value.trim().equals("1");
    }

    private MainPosition parseMainPosition(String position) {
        if (!StringUtils.hasText(position)) {
            return null;
        }
        try {
            return MainPosition.valueOf(position.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.warn("Invalid main position: {}", position);
            return null;
        }
    }

    private boolean isValidPosition(String position) {
        try {
            return VALID_POSITIONS.contains(position.trim().toUpperCase());
        } catch (Exception e) {
            return false;
        }
    }

    private Map<String, Integer> parsePositions(String positions) {
        Map<String, Integer> positionMap = new HashMap<>();
        if (!StringUtils.hasText(positions)) {
            return positionMap;
        }

        try {
            // Expected format: "POS1:90,POS2:80,POS3:70"
            Arrays.stream(positions.split(","))
                  .forEach(pos -> {
                      String[] parts = pos.split(":");
                      if (parts.length == 2 && isValidPosition(parts[0])) {
                          positionMap.put(parts[0].trim().toUpperCase(), 
                                  Integer.parseInt(parts[1].trim()));
                      }
                  });
        } catch (Exception e) {
            log.warn("Error parsing positions: {}", positions, e);
        }
        
        return positionMap;
    }
}
