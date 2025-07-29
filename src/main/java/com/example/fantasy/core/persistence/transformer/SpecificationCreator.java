package com.example.fantasy.core.persistence.transformer;

import org.springframework.data.jpa.domain.Specification;

/**
 * Interface for creating JPA Specifications from search parameters
 * 
 * @param <D> Domain model type
 * @param <SP> Search parameters type
 */
public interface SpecificationCreator<D, SP> {
    Specification<D> createSpecification(SP searchParams);
}
