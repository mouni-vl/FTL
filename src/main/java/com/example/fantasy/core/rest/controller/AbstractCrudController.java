package com.example.fantasy.core.rest.controller;

import com.example.fantasy.core.common.transformer.Transformer;
import com.example.fantasy.core.persistence.transformer.FilterCriteria;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract implementation of CRUD operations
 * @param <D> Domain type
 * @param <ID> ID type
 * @param <F> Filter type
 * @param <C> Create DTO type
 * @param <U> Update DTO type
 * @param <R> Response DTO type
 * @param <S> Service type
 */
@Slf4j
public abstract class AbstractCrudController<D, ID, F extends FilterCriteria<D>, C, U, R, S>
    extends AbstractReadController<D, ID, F, R, S>
    implements CrudResource<D,ID,F,C,U,R> {

    protected final Transformer<D, R> responseTransformer;
    protected final Transformer<D, C> createTransformer;
    protected final Transformer<D, U> updateTransformer;
    
    protected AbstractCrudController(
            S service, 
            Transformer<D, R> responseTransformer,
            Transformer<D, C> createTransformer,
            Transformer<D, U> updateTransformer) {
        super(service);
        this.responseTransformer = responseTransformer;
        this.createTransformer = createTransformer;
        this.updateTransformer = updateTransformer;
    }

    protected abstract D save(D entity);

    protected abstract void deleteById(ID id);

    protected abstract ID getId(D entity);
    
    @Override
    protected R toResponseDto(D domain) {
        return responseTransformer.toSource(domain);
    }
    
    @Override
    public ResponseEntity<Void> create(@Valid C createDto) {
        D entity = createTransformer.toDomain(createDto);
        D saved = save(entity);
        return created((Long) getId(saved), "/{id}");
    }
    
    @Override
    public ResponseEntity<Void> update(ID id, @Valid U updateDto) {
        String path = getCurrentRequestPath();
        return findById(id)
            .map(entity -> {
                log.debug("Updating entity with ID: {}", id);
                log.debug("Original entity: {}", entity);
                log.debug("Update DTO: {}", updateDto);
                
                D updatedDomain = updateTransformer.updateDomain(entity, updateDto);
                log.debug("Updated domain after mapping: {}", updatedDomain);
                
                return updatedDomain;
            })
            .map(this::save)
            .map(entity -> noContent())
            .orElseGet(() -> (ResponseEntity<Void>) notFound(getResourceName() + " not found", path));
    }
    
    @Override
    public ResponseEntity<Void> delete(ID id) {
        String path = getCurrentRequestPath();
        if (findById(id).isPresent()) {
            deleteById(id);
            return noContent();
        } else {
            return (ResponseEntity<Void>) notFound(getResourceName() + " not found", path);
        }
    }
}
