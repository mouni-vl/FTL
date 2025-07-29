package com.example.fantasy.adapter.inbound.rest.filter;

import com.example.fantasy.core.persistence.transformer.FilterCriteria;
import com.example.fantasy.domain.model.Club;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Filter criteria for club searches in REST API
 */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ClubFilter implements FilterCriteria<Club> {
    private String nameContains;
    private String footballingNation;
    private Integer minReputation;
    private Integer maxReputation;
    private String likelyFinishingGroup;

    @Override
    public Specification<Club> toSpecification() {
        Specification<Club> spec = where(null);
        
        if (nameContains != null && !nameContains.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> {
                String pattern = "%" + nameContains.toLowerCase() + "%";
                return cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("shortName")), pattern),
                    cb.like(cb.lower(root.get("nickname")), pattern)
                );
            });
        }
        
        if (footballingNation != null && !footballingNation.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> {
                String pattern = "%" + footballingNation.toLowerCase() + "%";
                return cb.like(cb.lower(root.get("footballingNation")), pattern);
            });
        }
        
        if (minReputation != null) {
            spec = spec.and((root, query, cb) -> 
                cb.greaterThanOrEqualTo(root.get("reputation"), minReputation)
            );
        }
        
        if (maxReputation != null) {
            spec = spec.and((root, query, cb) -> 
                cb.lessThanOrEqualTo(root.get("reputation"), maxReputation)
            );
        }
        
        if (likelyFinishingGroup != null && !likelyFinishingGroup.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> 
                cb.equal(root.get("likelyFinishingGroup"), likelyFinishingGroup)
            );
        }
        
        return spec;
    }
}
