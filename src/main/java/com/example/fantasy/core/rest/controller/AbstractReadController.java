package com.example.fantasy.core.rest.controller;

import com.example.fantasy.core.persistence.transformer.FilterCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.Optional;

/**
 * Abstract implementation of a readable and listable resource
 * @param <D> Domain type
 * @param <ID> ID type
 * @param <F> Filter type
 * @param <R> Response DTO type
 * @param <S> Service type
 */
public abstract class AbstractReadController<D, ID, F extends FilterCriteria<D>, R, S>
    extends BaseController
    implements ReadableResource<D, ID, R>, ListableResource<D, ID, F, R> {

    protected final S service;
    
    protected AbstractReadController(S service) {
        this.service = service;
    }

    protected abstract R toResponseDto(D domain);

    protected abstract Optional<D> findById(ID id);

    protected abstract Page<D> findAll(Specification<D> spec, Pageable pageable);
    
    @Override
    public ResponseEntity<?> getOne(ID id) {
        String path = getCurrentRequestPath();
        return findById(id)
            .map(this::toResponseDto)
            .map(this::success)
            .orElseGet(() -> (ResponseEntity<R>) notFound(getResourceName() + " not found", path));
    }
    
    @Override
    public ResponseEntity<Boolean> exists(ID id) {
        return success(findById(id).isPresent());
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> getList(@ModelAttribute F filter, Pageable pageable) {
        Specification<D> spec = filter != null ? filter.toSpecification() : null;
        Page<R> responsePage = findAll(spec, pageable)
            .map(this::toResponseDto);
        return paginatedResponse(responsePage);
    }

    protected String getCurrentRequestPath() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            return attrs.getRequest().getRequestURI();
        }
        return "";
    }
}
