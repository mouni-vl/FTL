package com.example.fantasy.application.port.out;

import com.example.fantasy.domain.model.Player;
import com.example.fantasy.domain.service.PlayerSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for Player persistence operations.
 * This is technology-agnostic and focuses only on domain operations.
 */
public interface PlayerRepository {
    List<Player> findAll();

    List<Player> findAll(PlayerSearchCriteria criteria, int page, int size);

    Page<Player> findAll(Specification<Player> domainSpec, Pageable pageable);

    Optional<Player> findById(Long id);

    Player save(Player player);

    List<Player> saveAll(List<Player> players);

    void update(Player player);

    void deleteById(Long id);

    //Optional<Player> findByFmId(Integer fmId);

    long count(PlayerSearchCriteria criteria);
}
