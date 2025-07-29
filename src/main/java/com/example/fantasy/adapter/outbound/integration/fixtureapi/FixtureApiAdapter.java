package com.example.fantasy.adapter.outbound.integration.fixtureapi;

import com.example.fantasy.application.port.out.external.FixtureProviderPort;
import com.example.fantasy.domain.model.Fixture;
import com.example.fantasy.adapter.outbound.integration.fixtureapi.client.FixtureApiClient;
import com.example.fantasy.adapter.outbound.integration.fixtureapi.dto.ExternalFixtureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FixtureApiAdapter implements FixtureProviderPort {
    private final FixtureApiClient fixtureApiClient;

    @Override
    public List<Fixture> fetchFixturesForGameweek(long seasonId, int gameweekNumber) {
        List<ExternalFixtureDto> externalDtos =
            fixtureApiClient.getFixturesBySeasonAndGameweek(seasonId, gameweekNumber);
        return externalDtos.stream()
                           .map(this::toDomain)
                           .collect(Collectors.toList());
    }

    @Override
    public List<Fixture> fetchFixturesByDateRange(LocalDate from, LocalDate to) {
        List<ExternalFixtureDto> externalDtos = fixtureApiClient.getFixturesByDateRange(from, to);
        return externalDtos.stream()
                           .map(this::toDomain)
                           .collect(Collectors.toList());
    }

    private Fixture toDomain(ExternalFixtureDto dto) {
        return Fixture.builder()
            .externalId(dto.getId())
            .seasonId(dto.getSeasonId())
            .homeClub(dto.getHomeClub())
            .awayClub(dto.getAwayClub())
            .kickoffAt(dto.getKickoffAt())
            .status(dto.getStatus())
            .gameweekNumber(dto.getGameweekNumber())
            .build();
    }
}
