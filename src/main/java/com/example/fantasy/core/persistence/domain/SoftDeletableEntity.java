package com.example.fantasy.core.persistence.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SoftDeletableEntity extends BaseEntity {
    @Column(name="deleted", nullable=false)
    private Boolean deleted = false;

    public void softDelete() {
        this.deleted = true;
    }
}