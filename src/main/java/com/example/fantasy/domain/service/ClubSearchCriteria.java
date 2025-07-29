package com.example.fantasy.domain.service;

import com.example.fantasy.core.persistence.transformer.FilterCriteria;
import com.example.fantasy.domain.model.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Search criteria for Club queries
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubSearchCriteria implements FilterCriteria<Club> {
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
    
    @Override
    public String toString() {
        return "ClubSearchCriteria{" +
                "nameContains='" + nameContains + '\'' +
                ", footballingNation='" + footballingNation + '\'' +
                ", minReputation=" + minReputation +
                ", maxReputation=" + maxReputation +
                ", likelyFinishingGroup='" + likelyFinishingGroup + '\'' +
                '}';
    }
}
