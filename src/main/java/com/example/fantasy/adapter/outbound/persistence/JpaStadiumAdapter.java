package com.example.fantasy.adapter.outbound.persistence;

import com.example.fantasy.adapter.outbound.persistence.entity.StadiumEntity;
import com.example.fantasy.adapter.outbound.persistence.mapper.StadiumEntityMapper;
import com.example.fantasy.adapter.outbound.persistence.repository.StadiumJpaRepository;
import com.example.fantasy.application.port.out.StadiumRepository;
import com.example.fantasy.domain.model.Stadium;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class JpaStadiumAdapter implements StadiumRepository {

    private final StadiumJpaRepository jpaRepo;
    private final StadiumEntityMapper mapper;

    @Override
    public List<Stadium> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Stadium> findById(Long id) {
        return jpaRepo.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    //If you want stadiums to be persisted even when the club insert fails
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public Stadium save(Stadium stadium) {
        StadiumEntity saved = jpaRepo.save(mapper.toSource(stadium));
        jpaRepo.flush();
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public void delete(Stadium stadium) {
        jpaRepo.delete(mapper.toSource(stadium));
    }

    @Override
    public Stadium update(Stadium stadium) {
        StadiumEntity updated = jpaRepo.save(mapper.toSource(stadium));
        return mapper.toDomain(updated);
    }

    @Override
    public List<Stadium> findByIds(List<Long> ids) {
        return jpaRepo.findAllById(ids)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Stadium> findByName(String name) {
        return jpaRepo.findByName(name)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}

