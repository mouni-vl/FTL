package com.example.fantasy.application.service;

import com.example.fantasy.adapter.outbound.persistence.entity.PositionEntity;
import com.example.fantasy.adapter.outbound.persistence.repository.PositionJpaRepository;
import com.example.fantasy.application.port.out.PositionRepository;
import com.example.fantasy.core.persistence.service.AbstractReaderService;
import com.example.fantasy.domain.model.Position;
import com.example.fantasy.domain.model.search.PositionSearchParams;
import org.springframework.stereotype.Service;


@Service
public class PositionService extends AbstractReaderService<Position, PositionEntity, Short, PositionSearchParams, PositionJpaRepository> {

    private final PositionRepository positionRepository;

    public PositionService(PositionJpaRepository repository,
                           PositionRepository positionRepository) {
        super(repository);
        this.positionRepository = positionRepository;
    }

    @Override
    protected Class<Position> getDomainClass() {
        return Position.class;
    }

    @Override
    protected Position toDomain(PositionEntity entity) {
        return new Position(entity.getPositionId(), entity.getPosition(), entity.getDescription());
    }

    @Override
    protected PositionEntity toEntity(Position domain) {
        return new PositionEntity(domain.positionId(), domain.position(), domain.description());
    }
}
