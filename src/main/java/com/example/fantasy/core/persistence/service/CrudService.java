package com.example.fantasy.core.persistence.service;

import java.util.Map;

/**
 * Base interface for CRUD operations on a domain entity.
 * Extends ReaderService to include read operations.
 *
 * @param <D> Domain model type
 * @param <ID> ID type of the domain model
 * @param <SP> Search parameters type
 */
public interface CrudService<D, ID, SP> extends ReaderService<D, ID, SP> {

    D create(D entity);

    D update(D entity);

    D patch(ID id, Map<String, Object> updates);

    void deleteById(ID id);

    void delete(D entity);
}
