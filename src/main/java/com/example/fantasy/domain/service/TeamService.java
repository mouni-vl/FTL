package com.example.fantasy.domain.service;

import com.example.fantasy.domain.model.Team;
import com.example.fantasy.domain.model.User;
import com.example.fantasy.domain.model.League;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    Team createTeam(Team team);
    Optional<Team> findById(Long id);
    List<Team> findByOwner(User owner);
    List<Team> findByLeague(League league);
    Team addPlayerToTeam(Long teamId, Long playerId);
    Team removePlayerFromTeam(Long teamId, Long playerId);
    Team updateTeam(Team team);
    void deleteTeam(Long id);
}
