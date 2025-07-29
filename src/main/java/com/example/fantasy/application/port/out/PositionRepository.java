package com.example.fantasy.application.port.out;

import com.example.fantasy.domain.model.Position;

import java.util.List;
import java.util.Optional;

/**
 * Port for reading all playable positions.
 * No save/update/delete, positions are static at runtime.
 */
public interface PositionRepository
{
    List<Position> findAll();
    Optional<Position> findById(short positionId);
}