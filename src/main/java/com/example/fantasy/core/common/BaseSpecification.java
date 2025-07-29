package com.example.fantasy.core.common;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Base specification class for building JPA specifications
 * @param <T> the entity type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseSpecification<T> implements Specification<T> {
    
    protected SearchCriteria criteria;
    
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria == null) {
            return null;
        }
        
        switch (criteria.getOperation()) {
            case EQUALS:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NOT_EQUALS:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case GREATER_THAN_EQUAL:
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN_EQUAL:
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case IN:
                return root.get(criteria.getKey()).in(criteria.getValue());
            case NOT_IN:
                return builder.not(root.get(criteria.getKey()).in(criteria.getValue()));
            case IS_NULL:
                return builder.isNull(root.get(criteria.getKey()));
            case IS_NOT_NULL:
                return builder.isNotNull(root.get(criteria.getKey()));
            default:
                return null;
        }
    }
    
    /**
     * Combine multiple specifications with AND
     * @param specifications the specifications to combine
     * @param <T> the entity type
     * @return the combined specification
     */
    public static <T> Specification<T> and(List<Specification<T>> specifications) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Specification<T> spec : specifications) {
                if (spec != null) {
                    Predicate predicate = spec.toPredicate(root, query, criteriaBuilder);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    
    /**
     * Combine multiple specifications with OR
     * @param specifications the specifications to combine
     * @param <T> the entity type
     * @return the combined specification
     */
    public static <T> Specification<T> or(List<Specification<T>> specifications) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Specification<T> spec : specifications) {
                if (spec != null) {
                    Predicate predicate = spec.toPredicate(root, query, criteriaBuilder);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                }
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
    
    /**
     * Search criteria for specifications
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchCriteria {
        private String key;
        private Object value;
        private SearchOperation operation;
    }
    
    /**
     * Search operations for specifications
     */
    public enum SearchOperation {
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        GREATER_THAN_EQUAL,
        LESS_THAN,
        LESS_THAN_EQUAL,
        LIKE,
        STARTS_WITH,
        ENDS_WITH,
        IN,
        NOT_IN,
        IS_NULL,
        IS_NOT_NULL
    }
}
