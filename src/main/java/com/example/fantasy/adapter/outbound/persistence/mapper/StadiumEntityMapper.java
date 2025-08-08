package com.example.fantasy.adapter.outbound.persistence.mapper;

import com.example.fantasy.adapter.outbound.persistence.entity.StadiumEntity;
import com.example.fantasy.domain.model.Stadium;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Stadium domain model and StadiumEntity
 */
@Component
public class StadiumEntityMapper {

    public Stadium toDomain(StadiumEntity entity) {
        if (entity == null) {
            return null;
        }

        return Stadium.builder()
                .id(entity.getId())
                .name(entity.getName())
                .capacity(entity.getCapacity())
                .build();
    }

    public StadiumEntity toSource(Stadium domain) {
        if (domain == null) {
            return null;
        }

        StadiumEntity entity = new StadiumEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCapacity(domain.getCapacity());

        return entity;
    }
}
