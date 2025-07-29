package com.example.fantasy.adapter.inbound.rest;

import com.example.fantasy.adapter.outbound.persistence.entity.PositionEntity;
import com.example.fantasy.application.service.PositionService;
import com.example.fantasy.core.persistence.transformer.FilterCriteria;
import com.example.fantasy.core.rest.controller.AbstractReadController;
import com.example.fantasy.domain.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/positions")
public class PositionController extends AbstractReadController<Position, Short, FilterCriteria<Position>,
        PositionEntity, PositionService> {

    public PositionController(PositionService positionService) {
        super(positionService);
    }

    @Override
    protected Optional<Position> findById(Short id) {
        return service.findById(id);
    }

    @Override
    protected Page<Position> findAll(Specification<Position> spec, Pageable pageable) {
        return service.findAll(spec, pageable);
    }

    @Override
    protected PositionEntity toResponseDto(Position domain) {
        return new PositionEntity(
                domain.positionId(),
                domain.position(),
                domain.description()
        );
    }

    @Override
    public String getResourceName() {
        return "Position";
    }
}
