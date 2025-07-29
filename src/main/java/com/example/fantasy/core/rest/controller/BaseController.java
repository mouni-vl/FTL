package com.example.fantasy.core.rest.controller;

import com.example.fantasy.core.exception.ErrorResponse;
import com.example.fantasy.shared.utils.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base controller with common methods for all controllers
 */
public abstract class BaseController {

    protected <T> ResponseEntity<T> success(T data) {
        return ResponseEntity.ok(data);
    }

    protected ResponseEntity<Void> created(Long id, String path) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    protected ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }

    protected <T> ResponseEntity<Map<String, Object>> paginatedResponse(Page<T> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", page.getContent());
        response.put("pagination", PaginationUtils.getPageMetadata(page));
        return ResponseEntity.ok(response);
    }

    protected <T> ResponseEntity<Map<String, Object>> paginatedResponse(Page<T> page, String dataKey) {
        Map<String, Object> response = new HashMap<>();
        response.put(dataKey, page.getContent());
        response.put("pagination", PaginationUtils.getPageMetadata(page));
        return ResponseEntity.ok(response);
    }

    protected <T> ResponseEntity<List<T>> listResponse(List<T> list) {
        return ResponseEntity.ok(list);
    }

    protected ResponseEntity<ErrorResponse> error(HttpStatus status, String message, String path) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(path)
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }

    protected ResponseEntity<ErrorResponse> badRequest(String message, String path) {
        return error(HttpStatus.BAD_REQUEST, message, path);
    }

    protected ResponseEntity<?> notFound(String message, String path) {
        return error(HttpStatus.NOT_FOUND, message, path);
    }

    protected ResponseEntity<ErrorResponse> forbidden(String message, String path) {
        return error(HttpStatus.FORBIDDEN, message, path);
    }

    protected ResponseEntity<ErrorResponse> unauthorized(String message, String path) {
        return error(HttpStatus.UNAUTHORIZED, message, path);
    }

    protected ResponseEntity<ErrorResponse> conflict(String message, String path) {
        return error(HttpStatus.CONFLICT, message, path);
    }
}
