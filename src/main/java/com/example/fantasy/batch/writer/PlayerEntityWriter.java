package com.example.fantasy.batch.writer;

import com.example.fantasy.batch.reader.PlayerCsvItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Receives lists of PlayerCsvItem, groups them by externalPlayerId,
 * then maps to domain Player and saves via PlayerJpaRepository (port → adapter).
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerEntityWriter implements ItemWriter<List<PlayerCsvItem>> {

//    private final PlayerJpaRepository playerRepository;

//    @Override
//    public void write(List<? extends List<PlayerCsvItem>> items) {
//        // Flatten the lists of rows
//        List<PlayerCsvItem> allRows = items.stream()
//                .flatMap(List::stream)
//                .collect(Collectors.toList());
//
//        // Group by externalPlayerId
//        Map<String, List<PlayerCsvItem>> grouped = allRows.stream()
//                .collect(Collectors.groupingBy(PlayerCsvItem::getExternalPlayerId));
//
//        log.info("Processing {} unique players from {} CSV rows", grouped.size(), allRows.size());
//
//        grouped.forEach((externalId, rows) -> {
//            // Build position ratings map
//            Map<String, Integer> positionRatings = new HashMap<>();
//            rows.forEach(row -> positionRatings.put(row.getPosition(), row.getRating()));
//
//            // Get first row for player details
//            PlayerCsvItem firstRow = rows.get(0);
//
//            // Check if player already exists
//            Player existingPlayer = playerRepository.findByExternalId(externalId).orElse(null);
//
//            if (existingPlayer != null) {
//                // Update existing player
//                log.debug("Updating existing player: {}", externalId);
//                existingPlayer.setFirstName(firstRow.getFirstName());
//                existingPlayer.setLastName(firstRow.getLastName());
//                existingPlayer.setClubId(firstRow.getClubId());
//                existingPlayer.setPositionRatings(positionRatings);
//
//                // Set primary position to the one with highest rating
//                String primaryPosition = positionRatings.entrySet().stream()
//                    .max(Map.Entry.comparingByValue())
//                    .map(Map.Entry::getKey)
//                    .orElse(null);
//                existingPlayer.setPosition(primaryPosition);
//
//                playerRepository.save(existingPlayer);
//            } else {
//                // Create new player
//                log.debug("Creating new player: {}", externalId);
//
//                // Set primary position to the one with highest rating
//                String primaryPosition = positionRatings.entrySet().stream()
//                    .max(Map.Entry.comparingByValue())
//                    .map(Map.Entry::getKey)
//                    .orElse(null);
//
//                Player player = Player.builder()
//                    .externalId(externalId)
//                    .firstName(firstRow.getFirstName())
//                    .lastName(firstRow.getLastName())
//                    .position(primaryPosition)
//                    .clubId(firstRow.getClubId())
//                    .positionRatings(positionRatings)
//                    .active(true)
//                    .build();
//
//                playerRepository.save(player);
//            }
//        });
//    }

    @Override
    public void write(Chunk<? extends List<PlayerCsvItem>> chunk) throws Exception {

    }
}
