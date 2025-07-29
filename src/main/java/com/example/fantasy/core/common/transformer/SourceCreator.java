package com.example.fantasy.core.common.transformer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Generic interface for transforming domain objects into another representation (e.g., DTOs or entities).
*
 * @param <DOMAIN> The type of the domain object being converted.
 * @param <SOURCE> The target type into which the domain object is converted.
 */
public interface SourceCreator<DOMAIN, SOURCE> {
    SOURCE toSource(DOMAIN domain);

    default List<SOURCE> toDTOList(List<DOMAIN> domainList) {
        if (domainList == null) {
            return null;
        }
        return domainList.stream()
                .map(this::toSource)
                .collect(Collectors.toList());
    }

    default Set<SOURCE> toSOURCESet(Set<DOMAIN> domainSet) {
        if (domainSet == null) {
            return null;
        }
        return domainSet.stream()
                .map(this::toSource)
                .collect(Collectors.toSet());
    }
}
