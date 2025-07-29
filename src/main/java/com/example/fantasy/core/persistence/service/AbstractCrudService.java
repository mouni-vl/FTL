package com.example.fantasy.core.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

/**
 * Abstract implementation of CrudService providing common CRUD operations
 * with lifecycle hooks
 *
 * @param <D>  Domain model type
 * @param <E>  Entity model type
 * @param <ID> ID type of the domain model
 * @param <SP> Search parameters type
 * @param <R>  Repository type extending JpaRepository and JpaSpecificationExecutor
 */
public abstract class AbstractCrudService<D, E, ID, SP, R extends JpaRepository<E, ID> & JpaSpecificationExecutor<E>>
        extends AbstractReaderService<D, E, ID, SP, R> implements CrudService<D, ID, SP> {

    protected AbstractCrudService(R repository) {
        super(repository);
    }

    @Override
    @Transactional
    public D create(D domain) {
        D prepared = beforeCreate(domain);
        E entity = toEntity(prepared);
        E savedEntity = repository.save(entity);
        D savedDomain = toDomain(savedEntity);
        return afterCreate(savedDomain);
    }

    @Override
    @Transactional
    public D update(D domain) {
        D prepared = beforeUpdate(domain);
        E entity = toEntity(prepared);
        E savedEntity = repository.save(entity);
        D savedDomain = toDomain(savedEntity);
        return afterUpdate(savedDomain);
    }

    @Override
    @Transactional
    public D patch(ID id, Map<String, Object> updates) {
        Optional<D> existingOpt = findById(id);
        if (existingOpt.isEmpty()) {
            throw new RuntimeException("Entity not found with id: " + id);
        }

        D existing = existingOpt.get();
        D prepared = beforePatch(existing, updates);
        E entity = toEntity(prepared);

        // Apply patches
        applyPatches(entity, updates);

        E savedEntity = repository.save(entity);
        D savedDomain = toDomain(savedEntity);
        return afterPatch(savedDomain, updates);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        Optional<E> entityOpt = repository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new RuntimeException("Entity not found with id: " + id);
        }

        E existing = entityOpt.get();
        D domain = toDomain(existing);
        
        beforeDelete(domain);
        repository.deleteById(id);
        afterDelete(domain);
    }

    @Override
    @Transactional
    public void delete(D domain) {
        E entity = toEntity(domain);
        beforeDelete(domain);
        repository.delete(entity);
        afterDelete(domain);
    }

    /**
     * Apply patches to an entity using reflection
     */
    protected void applyPatches(E entity, Map<String, Object> updates) {
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(entity.getClass(), key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            }
        });
    }

    // Lifecycle hooks - can be overridden by concrete implementations
    protected D beforeCreate(D domain) {
        return domain;
    }

    protected D afterCreate(D domain) {
        return domain;
    }

    protected D beforeUpdate(D domain) {
        return domain;
    }

    protected D afterUpdate(D domain) {
        return domain;
    }

    protected D beforePatch(D domain, Map<String, Object> updates) {
        return domain;
    }

    protected D afterPatch(D domain, Map<String, Object> updates) {
        return domain;
    }

    protected void beforeDelete(D domain) {
    }

    protected void afterDelete(D domain) {
    }
}
