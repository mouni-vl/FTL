package com.example.fantasy.core.persistence.service;

import com.example.fantasy.core.exception.ResourceNotFoundException;
import com.example.fantasy.core.persistence.domain.BaseEntity;
import com.example.fantasy.core.persistence.repository.BaseRepository;
import com.example.fantasy.domain.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractService<T extends BaseEntity, ID extends Serializable, T1 extends BaseRepository<Team, Long>> implements BaseService<T, ID> {
    
    protected final BaseRepository<T, ID> repository;
    
    @Override
    @Transactional
    public T create(T entity) {
        validateOnCreate(entity);
        return repository.save(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    @Transactional
    public T update(ID id, T entity) {
        validateOnUpdate(id, entity);
        
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(entity.getClass().getSimpleName(), id);
        }
        
        entity.setId((Long) id);
        return repository.save(entity);
    }
    
    @Override
    @Transactional
    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity", id);
        }
        repository.deleteById(id);
    }
    
    /**
     * Validate entity before creation
     * @param entity the entity to validate
     */
    protected abstract void validateOnCreate(T entity);
    
    /**
     * Validate entity before update
     * @param id the positionId of the entity to update
     * @param entity the entity with updated values
     */
    protected abstract void validateOnUpdate(ID id, T entity);
}
