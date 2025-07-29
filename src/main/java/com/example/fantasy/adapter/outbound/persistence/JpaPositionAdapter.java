package com.example.fantasy.adapter.outbound.persistence;

import com.example.fantasy.adapter.outbound.persistence.entity.PositionEntity;
import com.example.fantasy.adapter.outbound.persistence.mapper.PositionMapper;
import com.example.fantasy.adapter.outbound.persistence.repository.PositionJpaRepository;
import com.example.fantasy.application.port.out.PositionRepository;
import com.example.fantasy.domain.model.Position;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Persistence adapter implementing PositionRepository port.
 * Maps PositionEntity → Position (domain record).
 */
@Component
public class JpaPositionAdapter implements PositionRepository {

    private final PositionJpaRepository jpaRepo;
    private final PositionMapper positionMapper;

    public JpaPositionAdapter(PositionJpaRepository jpaRepo, PositionMapper positionMapper) {
        this.jpaRepo = jpaRepo;
        this.positionMapper = positionMapper;
    }

    @Override
    public List<Position> findAll() {
        return positionMapper.toDomainList(jpaRepo.findAll());
    }

    @Override
    public Optional<Position> findById(short positionId) {
        return jpaRepo.findById(positionId)
                .map(positionMapper::toDomain);
    }
}