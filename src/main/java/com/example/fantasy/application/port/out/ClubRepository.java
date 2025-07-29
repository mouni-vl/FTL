package com.example.fantasy.application.port.out;

import com.example.fantasy.domain.model.Club;
import com.example.fantasy.domain.service.ClubSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for Club persistence operations.
 * This is technology-agnostic and focuses only on domain operations.
 */
public interface ClubRepository {
    List<Club> findAll();

    List<Club> findAll(ClubSearchCriteria criteria, int page, int size);

    Page<Club> findAll(Specification<Club> domainSpec, Pageable pageable);

    Optional<Club> findById(Long id);

    Club save(Club club);

    void update(Club club);

    void deleteById(Long id);

    Optional<Club> findByFmId(Integer fmId);

    long count(ClubSearchCriteria criteria);
}
