package com.example.fantasy.core.common;

import com.example.fantasy.core.persistence.domain.BaseEntity;
import com.example.fantasy.shared.utils.DateTimeUtils;
import com.example.fantasy.shared.utils.SecurityUtils;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.Instant;

/**
 * Entity listener for auditing entities
 */
public class AuditingEntityListener {

    /**
     * Set audit fields before persisting an entity
     * @param entity the entity to audit
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
        Instant now = DateTimeUtils.nowUtc();
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        
        Long currentUserId = SecurityUtils.getCurrentUserId();
        String createdBy = currentUserId != null ? currentUserId.toString() : "system";
        entity.setCreatedBy(createdBy);
        entity.setUpdatedBy(createdBy);
    }

    /**
     * Set audit fields before updating an entity
     * @param entity the entity to audit
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setUpdatedAt(DateTimeUtils.nowUtc());
        
        Long currentUserId = SecurityUtils.getCurrentUserId();
        String updatedBy = currentUserId != null ? currentUserId.toString() : "system";
        entity.setUpdatedBy(updatedBy);
    }
}
