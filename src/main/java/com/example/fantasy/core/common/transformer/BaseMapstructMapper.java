package com.example.fantasy.core.common.transformer;

import org.mapstruct.MappingTarget;

/**
 * Base interface for MapStruct mappers that implements the Transformer interface.
 * This serves as a bridge between our transformer architecture and MapStruct.
 *
 * @param <DOMAIN> The domain entity type
 * @param <SOURCE> The SOURCE/DTO type
 */
public interface BaseMapstructMapper<DOMAIN, SOURCE> extends Transformer<DOMAIN, SOURCE> {
    SOURCE toSource(DOMAIN domain);

    @Override
    DOMAIN toDomain(SOURCE dto);

    @Override
    DOMAIN updateDomain(@MappingTarget DOMAIN domain, SOURCE dto);
}
