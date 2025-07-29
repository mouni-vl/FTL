package com.example.fantasy.core.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Abstract implementation of ReaderService providing common read operations
 *
 * @param <D> Domain model type
 * @param <E> Entity type
 * @param <ID> ID type of the domain model
 * @param <SP> Search parameters type
 * @param <R> Repository type extending JpaRepository and JpaSpecificationExecutor
 */
public abstract class AbstractReaderService<D, E, ID, SP, R extends JpaRepository<E, ID> & JpaSpecificationExecutor<E>>
        implements ReaderService<D, ID, SP> {

    protected final R repository;
    
    @PersistenceContext
    protected EntityManager entityManager;

    protected AbstractReaderService(R repository) {
        this.repository = repository;
    }

    protected abstract Class<D> getDomainClass();

    protected abstract D toDomain(E entity);

    protected abstract E toEntity(D domain);

    protected Specification<D> createSpecification(SP searchParams) {
        // Default implementation
        return Specification.where(null);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<D> findById(ID id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<D> findByIdDetached(ID id) {
        Optional<E> entity = repository.findById(id);
        if (entity.isPresent()) {
            E e = entity.get();
            detachEntity(e);
            return Optional.of(toDomain(e));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<D> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<D> findAll(SP searchParams) {
        if (searchParams == null) {
            return findAll();
        }
        return findAll(createSpecification(searchParams));
    }

    @Override
    @Transactional(readOnly = true)
    public List<D> findAll(Specification<D> domainSpec) {
        // Convert domain specification to entity specification (simplified)
        Specification<E> entitySpec = (root, query, cb) -> domainSpec.toPredicate(null, query, cb);
        
        return repository.findAll(entitySpec).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<D> findAll(Pageable pageable) {
        Page<E> page = repository.findAll(pageable);
        List<D> content = page.getContent().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
        
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<D> findAll(SP searchParams, Pageable pageable) {
        if (searchParams == null) {
            return findAll(pageable);
        }
        return findAll(createSpecification(searchParams), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<D> findAll(Specification<D> domainSpec, Pageable pageable) {
        // Convert domain specification to entity specification (simplified)
        Specification<E> entitySpec = (root, query, cb) -> domainSpec.toPredicate(null, query, cb);
        
        Page<E> page = repository.findAll(entitySpec, pageable);
        List<D> content = page.getContent().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
        
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<D> findAllSlice(Pageable pageable) {
        // This is a simplified implementation
        Page<D> page = findAll(pageable);
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<D> findAllSlice(SP searchParams, Pageable pageable) {
        if (searchParams == null) {
            return findAllSlice(pageable);
        }
        return findAllSlice(createSpecification(searchParams), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<D> findAllSlice(Specification<D> domainSpec, Pageable pageable) {
        // Convert domain specification to entity specification (simplified)
        Specification<E> entitySpec = (root, query, cb) -> domainSpec.toPredicate(null, query, cb);
        
        Page<E> page = repository.findAll(entitySpec, pageable);
        List<D> content = page.getContent().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
        
        return new PageImpl<>(content, pageable, page.getTotalElements());
    }

    protected void detachEntity(E entity) {
        if (entity != null && entityManager != null) {
            entityManager.detach(entity);
        }
    }
}
