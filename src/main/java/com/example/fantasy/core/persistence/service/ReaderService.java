package com.example.fantasy.core.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Base interface for read operations on a domain entity.
 *
 * @param <D> Domain model type
 * @param <ID> ID type of the domain model
 * @param <SP> Search parameters type
 */
public interface ReaderService<D, ID, SP> {

    Optional<D> findById(ID id);

    Optional<D> findByIdDetached(ID id);

    boolean existsById(ID id);

    List<D> findAll();

    List<D> findAll(SP searchParams);

    List<D> findAll(Specification<D> spec);

    Page<D> findAll(Pageable pageable);

    Page<D> findAll(SP searchParams, Pageable pageable);

    Page<D> findAll(Specification<D> spec, Pageable pageable);

    Slice<D> findAllSlice(Pageable pageable);

    Slice<D> findAllSlice(SP searchParams, Pageable pageable);

    Slice<D> findAllSlice(Specification<D> spec, Pageable pageable);
}
