package com.example.fantasy.core.common.transformer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Interface for transforming DTOs to domain entities.
 *
 * @param <DOMAIN> The domain entity type
 * @param <SOURCE> The SOURCE type
 */
public interface DomainCreator<DOMAIN, SOURCE> {
    DOMAIN toDomain(SOURCE source);

    default List<DOMAIN> toDomainList(List<SOURCE> sourceList) {
        if (sourceList == null) {
            return null;
        }
        return sourceList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    default Set<DOMAIN> toDomainSet(Set<SOURCE> sourceSet) {
        if (sourceSet == null) {
            return null;
        }
        return sourceSet.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }
}
