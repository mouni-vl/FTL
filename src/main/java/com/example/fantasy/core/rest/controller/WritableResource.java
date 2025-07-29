package com.example.fantasy.core.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Interface for write operations on a resource
 * @param <D> Domain type
 * @param <ID> ID type
 * @param <C> Create DTO type
 * @param <U> Update DTO type
 */
public interface WritableResource<D, ID, C, U> extends ResourceController<D, ID> {
    @PostMapping
    @Operation(summary = "Create a new resource")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Resource created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    ResponseEntity<Void> create(@Valid @RequestBody C createDto);

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update an existing resource")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Resource updated successfully"),
        @ApiResponse(responseCode = "404", description = "Resource not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    ResponseEntity<Void> update(@PathVariable ID id, @Valid @RequestBody U updateDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a resource")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Resource deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    ResponseEntity<Void> delete(@PathVariable ID id);
}
