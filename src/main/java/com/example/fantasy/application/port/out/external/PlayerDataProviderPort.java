package com.example.fantasy.application.port.out.external;

import com.example.fantasy.domain.model.Player;
import java.util.List;
import java.util.Optional;

/**
 * Port for fetching player data (profile, stats) from any source.
 */
public interface PlayerDataProviderPort {
    /** Fetch a single player by its external ID (String). */
    Optional<Player> fetchPlayerByExternalId(String externalPlayerId);

    /** Fetch all players for a given club external identifier. */
    List<Player> fetchPlayersByClub(String clubExternalId);
}
