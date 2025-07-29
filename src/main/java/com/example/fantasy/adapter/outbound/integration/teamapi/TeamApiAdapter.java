package com.example.fantasy.adapter.outbound.integration.teamapi;

import com.example.fantasy.application.port.out.external.TeamDataProviderPort;
import com.example.fantasy.domain.model.Team;
import com.example.fantasy.adapter.outbound.integration.teamapi.client.TeamApiClient;
import com.example.fantasy.adapter.outbound.integration.teamapi.dto.ExternalTeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeamApiAdapter implements TeamDataProviderPort {
    private final TeamApiClient teamApiClient;

    @Override
    public Optional<Team> fetchTeamByExternalId(String externalTeamId) {
        return teamApiClient.getTeamById(externalTeamId)
                .map(this::toDomain);
    }

    private Team toDomain(ExternalTeamDto dto) {
        return Team.builder()
            .externalId(dto.getId())
            .name(dto.getName())
            .clubId(dto.getClubId())
            .build();
    }
}
