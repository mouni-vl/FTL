package com.example.fantasy.application.service;


import com.example.fantasy.application.dto.GameweekStatsDto;
import com.example.fantasy.application.dto.PlayerDto;
import com.example.fantasy.application.dto.SeasonStatsDto;

import java.util.List;
import java.util.Optional;

/**
 * A facade that can orchestrate cross‐resource operations.
 * For example, get a “full profile” containing Player + Seasons + Gameweeks.
 */
public interface PlayerFacade {
    Optional<PlayerDto> getPlayerBasic(Integer playerId);
    List<SeasonStatsDto> getAllSeasons(Integer playerId);
    List<GameweekStatsDto> getAllGameweeks(Integer playerId, String season);

    /**
     * Example of combined use‐case:
     * Return PlayerDto + flatten all SeasonStatsDto + flatten all GameweekStatsDto.
     */
    //PlayerFullProfileDto getFullProfile(Integer playerId);
}
