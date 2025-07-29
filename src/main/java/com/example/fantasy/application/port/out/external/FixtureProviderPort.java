package com.example.fantasy.application.port.out.external;

import com.example.fantasy.domain.model.Fixture;
import java.time.LocalDate;
import java.util.List;

/**
 * Port for fetching fixture data from any source (HTTP API or CSV).
 */
public interface FixtureProviderPort {
    /** Fetch all fixtures for a given season and gameweek */
    List<Fixture> fetchFixturesForGameweek(long seasonId, int gameweekNumber);

    /** Fetch fixtures between two dates (inclusive) */
    List<Fixture> fetchFixturesByDateRange(LocalDate from, LocalDate to);
}
