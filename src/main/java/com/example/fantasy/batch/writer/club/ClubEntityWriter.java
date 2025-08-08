package com.example.fantasy.batch.writer.club;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import com.example.fantasy.adapter.outbound.persistence.repository.ClubJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

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

        log.info("Saving {} clubs to database", clubs.size());

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
            clubRepository.saveAll(clubs);

            log.info("Successfully saved {} clubs to database", clubs.size());
        } catch (Exception e) {
            log.error("Error saving clubs to database: {}", e.getMessage(), e);
            throw e;
        }
    }
}
