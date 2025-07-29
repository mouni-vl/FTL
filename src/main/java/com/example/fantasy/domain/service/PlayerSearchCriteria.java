package com.example.fantasy.domain.service;

import com.example.fantasy.core.persistence.transformer.FilterCriteria;
import com.example.fantasy.domain.model.MainPosition;
import com.example.fantasy.domain.model.Player;
import com.example.fantasy.domain.model.Position;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerSearchCriteria implements FilterCriteria<Player> {
    private String nameContains;
    private MainPosition mainPosition;
    private Set<Position> playablePositions;
    private String club;
    private String nationality;
    private Double minPrice;
    private Double maxPrice;
    private Integer minTotalPoints;
    private Integer maxTotalPoints;
    private String availabilityStatus;
    private Boolean active;

    @Override
    public Specification<Player> toSpecification() {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();
            if (nameContains != null) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("fullName")), "%" + nameContains.toLowerCase() + "%"));
            }
            if (mainPosition != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("mainPosition"), mainPosition));
            }
            // … add other criteria similarly …
            return predicates;
        };
    }
}
