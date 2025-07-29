package com.example.fantasy.core.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Abstract service that handles DTO to Entity conversion
 * 
 * @param <DTO> DTO type
 * @param <D> Domain model type
 * @param <ID> ID type of the domain model
 * @param <SP> Search parameters type
 * @param <R> Repository type extending JpaRepository and JpaSpecificationExecutor
 */
public abstract class AbstractDtoService<DTO, D, ID, SP, R extends JpaRepository<D, ID> & JpaSpecificationExecutor<D>> {

    protected final CrudService<D, ID, SP> domainService;
    
    protected AbstractDtoService(CrudService<D, ID, SP> domainService) {
        this.domainService = domainService;
    }

    protected abstract DTO toDto(D entity);

    protected abstract D toEntity(DTO dto);

    @Transactional(readOnly = true)
    public Optional<DTO> findById(ID id) {
        return domainService.findById(id).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return domainService.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return domainService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DTO> findAll(SP searchParams) {
        return domainService.findAll(searchParams).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DTO> findAll(Specification<D> spec) {
        return domainService.findAll(spec).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<DTO> findAll(Pageable pageable) {
        Page<D> page = domainService.findAll(pageable);
        return new PageImpl<>(
                page.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                pageable,
                page.getTotalElements()
        );
    }

    @Transactional(readOnly = true)
    public Page<DTO> findAll(SP searchParams, Pageable pageable) {
        Page<D> page = domainService.findAll(searchParams, pageable);
        return new PageImpl<>(
                page.getContent().stream().map(this::toDto).collect(Collectors.toList()),
                pageable,
                page.getTotalElements()
        );
    }

    @Transactional
    public DTO create(DTO dto) {
        D entity = toEntity(dto);
        D savedEntity = domainService.create(entity);
        return toDto(savedEntity);
    }

    @Transactional
    public DTO update(DTO dto) {
        D entity = toEntity(dto);
        D savedEntity = domainService.update(entity);
        return toDto(savedEntity);
    }

    @Transactional
    public DTO patch(ID id, Map<String, Object> updates) {
        D patchedEntity = domainService.patch(id, updates);
        return toDto(patchedEntity);
    }

    @Transactional
    public void deleteById(ID id) {
        domainService.deleteById(id);
    }
}
