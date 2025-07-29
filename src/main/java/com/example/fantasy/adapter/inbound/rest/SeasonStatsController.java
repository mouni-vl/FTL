package com.example.fantasy.adapter.inbound.rest;

import com.example.fantasy.application.dto.SeasonStatsDto;
import com.example.fantasy.application.service.SeasonStatsService;
import com.example.fantasy.core.persistence.transformer.FilterCriteria;
import com.example.fantasy.core.rest.controller.AbstractReadController;
import com.example.fantasy.domain.model.SeasonStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/players/{playerId}/seasons")
public class SeasonStatsController extends AbstractReadController<SeasonStats, Short, FilterCriteria<SeasonStats>,
        SeasonStatsDto, SeasonStatsService> {
    protected SeasonStatsController(SeasonStatsService service) {
        super(service);
    }

    @Override
    protected SeasonStatsDto toResponseDto(SeasonStats domain) {
        return null;
    }

    @Override
    protected Optional<SeasonStats> findById(Short aShort) {
        return Optional.empty();
    }

    @Override
    protected Page<SeasonStats> findAll(Specification<SeasonStats> spec, Pageable pageable) {
        return null;
    }

    @Override
    public String getResourceName() {
        return "";
    }
}
