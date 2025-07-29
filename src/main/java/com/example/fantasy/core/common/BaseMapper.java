package com.example.fantasy.core.common;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base mapper interface for converting between entities and DTOs
 * @param <E> the entity type
 * @param <D> the DTO type
 */
public interface BaseMapper<E, D> {
    
    /**
     * Convert an entity to a DTO
     * @param entity the entity to convert
     * @return the DTO
     */
    D toDto(E entity);
    
    /**
     * Convert a DTO to an entity
     * @param dto the DTO to convert
     * @return the entity
     */
    E toEntity(D dto);
    
    /**
     * Convert a list of entities to a list of DTOs
     * @param entities the entities to convert
     * @return the DTOs
     */
    default List<D> toDtoList(List<E> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Convert a list of DTOs to a list of entities
     * @param dtos the DTOs to convert
     * @return the entities
     */
    default List<E> toEntityList(List<D> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    
    /**
     * Update an entity from a DTO
     * @param dto the DTO with updated values
     * @param entity the entity to update
     * @return the updated entity
     */
    E updateEntityFromDto(D dto, E entity);
}
