package com.example.fantasy.adapter.outbound.persistence.repository;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for Club entities
 */
@Repository
public interface ClubJpaRepository extends JpaRepository<ClubEntity, Long> {

    Optional<ClubEntity> findByName(String name);

    List<ClubEntity> findByFootballingNation(Integer footballingNationId);

    Optional<ClubEntity> findByFmId(Long fmId);
}
