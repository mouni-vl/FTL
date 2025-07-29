package com.example.fantasy.adapter.outbound.persistence.mapper;

import com.example.fantasy.adapter.outbound.persistence.entity.PositionEntity;
import com.example.fantasy.core.common.transformer.DomainCreator;
import com.example.fantasy.domain.model.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PositionMapper extends DomainCreator<Position, PositionEntity> {
    @Override
    @Mapping(target = "position", source = "position")
    @Mapping(target = "description", source = "description")
    Position toDomain(PositionEntity positionEntity);
}

