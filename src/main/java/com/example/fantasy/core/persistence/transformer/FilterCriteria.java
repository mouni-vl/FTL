package com.example.fantasy.core.persistence.transformer;

import org.springframework.data.jpa.domain.Specification;

/**
 * Interface for filter criteria that can be converted to a JPA Specification
 * @param <T> the entity type
 */
public interface FilterCriteria<T> {
    /**
     * Convert this filter to a JPA Specification
     * @return the specification
     */
    Specification<T> toSpecification();
    
    /**
     * Get an empty specification that matches all entities
     * @return a specification that always returns true
     */
    default Specification<T> all() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}
