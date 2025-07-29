package com.example.fantasy.adapter.inbound.rest;

import com.example.fantasy.application.dto.PlayerDto;
import com.example.fantasy.application.service.PlayerServiceImpl;
import com.example.fantasy.core.common.transformer.Transformer;
import com.example.fantasy.core.rest.controller.AbstractCrudController;
import com.example.fantasy.domain.model.Player;
import com.example.fantasy.domain.service.PlayerSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController extends AbstractCrudController<Player, Long, PlayerSearchCriteria,
        PlayerDto, PlayerDto, PlayerDto, PlayerServiceImpl> {

    protected PlayerController(PlayerServiceImpl service, Transformer<Player, PlayerDto> responseTransformer,
                               Transformer<Player, PlayerDto> createTransformer,
                               Transformer<Player, PlayerDto> updateTransformer) {
        super(service, responseTransformer, createTransformer, updateTransformer);
    }

    @Override
    protected Player save(Player domain) {
        if (domain.getId() == null) {
            return service.create(domain);
        } else {
            service.update(domain);
            return domain;
        }
    }

    @Override
    protected void deleteById(Long id) {
        service.deleteById(id);
    }

    @Override
    protected Long getId(Player domain) {
        return domain.getId();
    }

    @Override
    protected Optional<Player> findById(Long id) {
        return service.findById(id);
    }

    @Override
    protected Page<Player> findAll(Specification<Player> spec, Pageable pageable) {
        return service.findAll(spec, pageable);
    }

    protected List<Player> findAll() {
        return service.findAll();
    }

    @Override
    public String getResourceName() {
        return "Player";
    }
}
