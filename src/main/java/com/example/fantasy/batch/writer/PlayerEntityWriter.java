package com.example.fantasy.batch.writer;

import com.example.fantasy.application.port.out.PlayerRepository;
import com.example.fantasy.domain.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Spring Batch ItemWriter implementation that saves Player domain objects to the database.
 * Uses the PlayerRepository port to persist processed Player entities in batches.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerEntityWriter implements ItemWriter<Player> {

    private final PlayerRepository playerRepository;

    @Override
    public void write(Chunk<? extends Player> chunk) throws Exception {
        List<Player> players = (List<Player>) chunk.getItems();

        if (players.isEmpty()) {
            log.info("No players to save");
            return;
        }

        log.info("Saving {} players to database", players.size());

        try {
            // Log first few players for debugging
            if (log.isDebugEnabled()) {
                players.stream().limit(5).forEach(player ->
                        log.debug("Saving player: {} (FM ID: {})", player.getFullName(), player.getFmId()));
            }

            // Save all players
            playerRepository.saveAll(players);

            log.info("Successfully saved {} players to database", players.size());
        } catch (Exception e) {
            log.error("Error saving players to database: {}", e.getMessage(), e);
            throw e;
        }
    }
}
