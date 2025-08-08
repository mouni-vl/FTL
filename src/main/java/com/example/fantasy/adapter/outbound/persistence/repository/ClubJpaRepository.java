package com.example.fantasy.adapter.outbound.persistence.repository;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for Club entities
 */
@Repository
public interface ClubJpaRepository extends JpaRepository<ClubEntity, Long>, JpaSpecificationExecutor<ClubEntity> {

    /**
     * Find club by name
     */
    Optional<ClubEntity> findByName(String name);

    /**
     * Find clubs by footballing nation ID
     */
    List<ClubEntity> findByFootballingNation(Integer footballingNationId);
}
