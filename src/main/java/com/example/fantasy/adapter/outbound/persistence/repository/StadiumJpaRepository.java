package com.example.fantasy.adapter.outbound.persistence.repository;

import com.example.fantasy.adapter.outbound.persistence.entity.StadiumEntity;
import com.example.fantasy.core.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StadiumJpaRepository extends BaseRepository<StadiumEntity, Long> {
    Optional<StadiumEntity> findByName(String name);
}
