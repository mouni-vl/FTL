package com.example.fantasy.core.common.transformer;

/**
 * Interface for updating domain entities with data from DTOs.
 *
 * @param <DOMAIN> The domain entity type
 * @param <SOURCE> The SOURCE type
 */
public interface DomainUpdater<DOMAIN, SOURCE> {
    DOMAIN updateDomain(DOMAIN domain, SOURCE source);
}
