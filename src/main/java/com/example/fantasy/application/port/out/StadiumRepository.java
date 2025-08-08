package com.example.fantasy.application.port.out;


import com.example.fantasy.domain.model.Club;
import com.example.fantasy.domain.model.Stadium;
import com.example.fantasy.domain.service.ClubSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for Club persistence operations.
 */
public interface StadiumRepository {
    List<Stadium> findAll();

    //List<Stadium> findAll(ClubSearchCriteria criteria, int page, int size);

    //Page<Stadium> findAll(Specification<Stadium> domainSpec, Pageable pageable);

    Optional<Stadium> findById(Long id);

    Stadium save(Stadium stadium);

    void deleteById(Long id);

    void delete(Stadium stadium);

    Stadium update(Stadium stadium);

    List<Stadium> findByIds(List<Long> ids);

    List<Stadium> findByName(String name);
}
