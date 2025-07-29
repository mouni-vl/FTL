package com.example.fantasy.application.port.out;

import com.example.fantasy.domain.model.League;
import com.example.fantasy.domain.model.Team;
import com.example.fantasy.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // delete later
public interface TeamRepository
        //extends BaseRepository<Team, Long>
        {
    List<Team> findByOwner(User owner);
    List<Team> findByLeague(League league);
    Optional<Team> findByOwnerAndLeague(User owner, League league);
}
