package com.example.fantasy.adapter.outbound.persistence.repository;

import com.example.fantasy.adapter.outbound.persistence.entity.PlayerEntity;
import com.example.fantasy.core.persistence.repository.BaseRepository;
import com.example.fantasy.domain.model.MainPosition;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerJpaRepository extends BaseRepository<PlayerEntity, Long>, JpaSpecificationExecutor<PlayerEntity> {
    
    // Find methods that respect soft delete
    @Query("SELECT p FROM PlayerEntity p WHERE p.deleted = false AND p.mainPosition = ?1")
    List<PlayerEntity> findByMainPosition(MainPosition mainPosition);
    
    @Query("SELECT p FROM PlayerEntity p WHERE p.deleted = false AND p.fmId = ?1")
    Optional<PlayerEntity> findByFmId(Integer fmId);
    
    @Query("SELECT p FROM PlayerEntity p WHERE p.deleted = false AND p.availabilityStatus = ?1")
    List<PlayerEntity> findByAvailabilityStatus(String status);
    
    // Default findById and findAll methods should also respect soft delete
    @Override
    @Query("SELECT p FROM PlayerEntity p WHERE p.deleted = false AND p.id = ?1")
    Optional<PlayerEntity> findById(Long id);
    
    @Override
    @Query("SELECT p FROM PlayerEntity p WHERE p.deleted = false")
    List<PlayerEntity> findAll();

    @Override
    @Modifying
    @Query("UPDATE PlayerEntity p SET p.deleted = true WHERE p.id = :id")
    void softDeleteById(@Param("id") Long id);
}
