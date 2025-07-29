package com.example.fantasy.core.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * Interface for listing resources with filtering
 * @param <D> Domain type
 * @param <ID> ID type
 * @param <F> Filter criteria type
 * @param <R> Response DTO type
 */
public interface ListableResource<D, ID, F, R> extends ResourceController<D, ID> {

    @GetMapping
    @Operation(summary = "Get a paginated list of resources")
    ResponseEntity<Map<String, Object>> getList(F filter, Pageable pageable);
}
