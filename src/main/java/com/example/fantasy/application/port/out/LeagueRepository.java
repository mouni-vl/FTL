package com.example.fantasy.application.port.out;

import com.example.fantasy.domain.model.League;
import com.example.fantasy.domain.model.User;
import java.util.Optional;
import java.util.List;

public interface LeagueRepository {
    Optional<League> findById(Long id);
    List<League> findByOwner(User owner);
    List<League> findByIsPublic(boolean isPublic);
    List<League> findAll();
    League save(League league);
    void deleteById(Long id);
}
