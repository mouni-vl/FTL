package com.example.fantasy.shared.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for pagination operations
 */
public final class PaginationUtils {
    
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int MAX_PAGE_SIZE = 100;
    
    private PaginationUtils() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Create a pageable object with default sorting
     * @param page the page number (0-based)
     * @param size the page size
     * @param sortField the field to sort by
     * @param sortDirection the sort direction
     * @return a pageable object
     */
    public static Pageable createPageable(int page, int size, String sortField, String sortDirection) {
        int pageSize = validatePageSize(size);
        Sort sort = createSort(sortField, sortDirection);
        return PageRequest.of(Math.max(0, page), pageSize, sort);
    }
    
    /**
     * Create a pageable object without sorting
     * @param page the page number (0-based)
     * @param size the page size
     * @return a pageable object
     */
    public static Pageable createPageable(int page, int size) {
        int pageSize = validatePageSize(size);
        return PageRequest.of(Math.max(0, page), pageSize);
    }
    
    /**
     * Create a sort object
     * @param sortField the field to sort by
     * @param sortDirection the sort direction
     * @return a sort object
     */
    public static Sort createSort(String sortField, String sortDirection) {
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sortDirection)) {
            direction = Sort.Direction.DESC;
        }
        
        if (StringUtils.isEmpty(sortField)) {
            return Sort.unsorted();
        }
        
        return Sort.by(direction, sortField);
    }
    
    /**
     * Create a pagination metadata map from a page object
     * @param page the page object
     * @return a map with pagination metadata
     */
    public static Map<String, Object> getPageMetadata(Page<?> page) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("totalItems", page.getTotalElements());
        metadata.put("totalPages", page.getTotalPages());
        metadata.put("currentPage", page.getNumber());
        metadata.put("pageSize", page.getSize());
        metadata.put("hasNext", page.hasNext());
        metadata.put("hasPrevious", page.hasPrevious());
        return metadata;
    }
    
    /**
     * Validate the page size
     * @param size the page size
     * @return a valid page size
     */
    private static int validatePageSize(int size) {
        if (size <= 0) {
            return DEFAULT_PAGE_SIZE;
        }
        return Math.min(size, MAX_PAGE_SIZE);
    }
}
