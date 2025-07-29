package com.example.fantasy.core.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    
    /**
     * Soft delete operation - to be implemented by concrete repositories
     * that support soft delete functionality
     * 
     * @param id entity ID to soft delete
     */
    default void softDeleteById(ID id) {
        // Default implementation does a hard delete
        // Override in specific repositories that support soft delete
        deleteById(id);
    }
}
