package com.example.fantasy.domain.model;

/**
 * Immutable data for one playable position (DL, DC, MR, SC, ...).
 */
public record Position(
        short positionId,
        String position,
        String description
) {}