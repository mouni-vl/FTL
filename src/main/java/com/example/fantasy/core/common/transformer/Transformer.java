package com.example.fantasy.core.common.transformer;

/**
 * Base transformer interface that combines all transformation operations.
 * This serves as the main contract for all transformers in the application.
 *
 * @param <DOMAIN> The domain entity type
 * @param <SOURCE> The SOURCE type
 */
public interface Transformer<DOMAIN, SOURCE> extends
        SourceCreator<DOMAIN, SOURCE>,
        DomainCreator<DOMAIN, SOURCE>,
        DomainUpdater<DOMAIN, SOURCE> {

    default SOURCE postProcessSource(SOURCE source, DOMAIN domain) {
        return source;
    }

    default DOMAIN postProcessDomain(DOMAIN domain, SOURCE source) {
        return domain;
    }
}