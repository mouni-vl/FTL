package com.example.fantasy.batch.writer.club;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import com.example.fantasy.adapter.outbound.persistence.repository.ClubJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring Batch ItemWriter implementation for saving clubs to the database
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ClubEntityWriter implements ItemWriter<ClubEntity> {

    private final ClubJpaRepository clubRepository;

    @Override
    public void write(Chunk<? extends ClubEntity> chunk) throws Exception {
        List<? extends ClubEntity> clubs = chunk.getItems();

        if (clubs.isEmpty()) {
            log.info("No clubs to save");
            return;
        }

        Map<String, Integer> stats = countNewAndExisting(clubs);

        log.info("Saving {} clubs to database ({} new, {} updates)",
                clubs.size(), stats.get("new"), stats.get("update"));

        try {
            // Log first few clubs for debugging
            if (log.isDebugEnabled()) {
                clubs.stream().limit(5).forEach(club ->
                        log.debug("Saving club: {} (ID: {}, Stadium: {})",
                                club.getName(),
                                club.getId(),
                                club.getStadium() != null ? club.getStadium().getName() : "None"));
            }

            // Save all clubs
            List<ClubEntity> savedClubs = clubRepository.saveAll(new ArrayList<>(clubs));

            log.info("Successfully saved {} clubs to database", savedClubs.size());
        } catch (Exception e) {
            log.error("Error saving clubs to database: {}", e.getMessage(), e);
            throw e;
        }
    }


    private Map<String, Integer> countNewAndExisting(List<? extends ClubEntity> clubs) {
        // Use existing JPA context to determine if entity is new or detached
        int newCount = 0;
        int updateCount = 0;

        for (ClubEntity club : clubs) {
            // Consider clubs with creation date same as update date as new
            if (club.getCreatedAt() != null &&
                    club.getUpdatedAt() != null &&
                    club.getCreatedAt().equals(club.getUpdatedAt())) {
                newCount++;
            } else {
                updateCount++;
            }
        }

        Map<String, Integer> stats = new HashMap<>();
        stats.put("new", newCount);
        stats.put("update", updateCount);
        return stats;
    }
}
