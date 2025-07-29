package com.example.fantasy.application.service;

import com.example.fantasy.application.port.out.PlayerRepository;
import com.example.fantasy.domain.model.Player;
import com.example.fantasy.domain.service.PlayerSearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerServiceImpl {

    private final PlayerRepository playerRepository;

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Page<Player> findAll(Specification<Player> spec, Pageable pageable) {
        return playerRepository.findAll(spec, pageable);
    }
    
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }
    
    public Optional<Player> findByFmId(Integer fmId) {
        return playerRepository.findByFmId(fmId);
    }
    
    @Transactional
    public Player create(Player player) {
        log.debug("Creating new player: {}", player);
        return playerRepository.save(player);
    }

    @Transactional
    public void update(Player player) {
        if (player.getId() == null) {
            throw new IllegalArgumentException("Cannot update player with null ID");
        }
        
        log.debug("Updating player with ID: {}, fmId: {}", player.getId(), player.getFmId());
        
        Optional<Player> existingPlayerOpt = playerRepository.findById(player.getId());
        if (existingPlayerOpt.isEmpty()) {
            throw new IllegalArgumentException("Cannot update non-existent player with ID: " + player.getId());
        }
        
        playerRepository.update(player);
        log.debug("{} updated successfully", player.getFullName());
    }
    
    @Transactional
    public void deleteById(Long id) {
        log.debug("Soft deleting player with ID: {}", id);
        playerRepository.deleteById(id);
    }

    @Transactional
    public Optional<Player> updatePlayerPosition(Long playerId, String positionCode, Integer familiarity) {
        return playerRepository.findById(playerId).map(player -> {
            Map<String, Integer> currentPositions = player.getPositions();
            Map<String, Integer> updatedPositions = new HashMap<>(
                currentPositions != null ? currentPositions : new HashMap<>()
            );
            updatedPositions.put(positionCode.toUpperCase(), familiarity);
            player.setPositions(updatedPositions);
            
            playerRepository.update(player);
            return player;
        });
    }
    
    /**
     * Converts a Spring Data JPA Specification to our domain PlayerSearchCriteria
     * This is an adapter method that allows us to use Spring's specification in the service
     * while keeping the domain layer clean.
     */
    private PlayerSearchCriteria specificationToCriteria(Specification<Player> spec) {
        // This is a simplified implementation
        // In a real app, you might need more sophisticated conversion
        return PlayerSearchCriteria.builder().build();
    }
}