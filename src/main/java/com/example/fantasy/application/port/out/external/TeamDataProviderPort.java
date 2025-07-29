package com.example.fantasy.application.port.out.external;

import com.example.fantasy.domain.model.Team;
import java.util.Optional;

/**
 * Port for fetching team-level data (rosters, standings) from any source.
 */
public interface TeamDataProviderPort {
    Optional<Team> fetchTeamByExternalId(String externalTeamId);
}
