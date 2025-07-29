package com.example.fantasy.core.rest.controller;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Standardized response format for paginated data
 * @param content the page content
 * @param totalElements total elements across all pages
 * @param totalPages total number of pages
 * @param currentPage current page number (0-based)
 * @param pageSize size of each page
 * @param <T> type of the content
 */
public record PageResponse<T>(
    List<T> content,
    long totalElements,
    int totalPages,
    int currentPage,
    int pageSize
) {
    /**
     * Create a PageResponse from a Spring Page
     * @param page the Spring Page
     * @return a new PageResponse
     */
    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
            page.getContent(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.getNumber(),
            page.getSize()
        );
    }
}
