package com.example.fantasy.adapter.outbound.persistence.repository;

import com.example.fantasy.adapter.outbound.persistence.entity.ClubEntity;
import com.example.fantasy.core.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubJpaRepository extends BaseRepository<ClubEntity, Long> {
    
    Optional<ClubEntity> findByFmId(Integer fmId);

}
