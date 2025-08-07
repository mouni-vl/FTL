package com.example.fantasy.core.persistence.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SoftDeletableEntity extends BaseEntity {
    @Column(name="nfe", nullable=false)
    private Boolean nfe = false;

    public void softDelete() {
        this.nfe = true;
    }
}