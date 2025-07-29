package com.example.fantasy.adapter.outbound.persistence.mapper;

import com.example.fantasy.core.common.transformer.Transformer;

/**
 * Interface for mapping between domain entities and persistence entities.
 * This extends our Transformer interface for use in the persistence layer.
 *
 * @param <DOMAIN> The domain entity type
 * @param <ENTITY> The persistence entity type
 */
public interface EntityMapper<DOMAIN, ENTITY> extends Transformer<DOMAIN, ENTITY> {
    @Override
    ENTITY toSource(DOMAIN domain);

    @Override
    DOMAIN toDomain(ENTITY entity);

    @Override
    DOMAIN updateDomain(DOMAIN domain, ENTITY entity);
}
