package com.example.fantasy.core.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface for read operations on a resource
 * @param <D> Domain type
 * @param <ID> ID type
 * @param <R> Response DTO type
 */
public interface ReadableResource<D, ID, R> extends ResourceController<D, ID> {
    @GetMapping("/{id}")
    @Operation(summary = "Get a single resource by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource found"),
        @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    ResponseEntity<?> getOne(@PathVariable ID id);

    @GetMapping("/{id}/exists")
    @Operation(summary = "Check if a resource exists")
    ResponseEntity<Boolean> exists(@PathVariable ID id);
}
