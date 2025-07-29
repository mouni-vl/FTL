package com.example.fantasy.adapter.outbound.integration.playerapi;

import com.example.fantasy.application.port.out.external.PlayerDataProviderPort;
import com.example.fantasy.domain.model.Player;
import com.example.fantasy.adapter.outbound.integration.playerapi.client.PlayerApiClient;
import com.example.fantasy.adapter.outbound.integration.playerapi.dto.ExternalPlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlayerApiAdapter implements PlayerDataProviderPort {
    private final PlayerApiClient playerApiClient;

    @Override
    public Optional<Player> fetchPlayerByExternalId(String externalPlayerId) {
        return playerApiClient.getPlayerById(externalPlayerId)
                .map(this::toDomain);
    }

    @Override
    public List<Player> fetchPlayersByClub(String clubExternalId) {
        List<ExternalPlayerDto> dtos = playerApiClient.getPlayersByClub(clubExternalId);
        return dtos.stream()
                   .map(this::toDomain)
                   .collect(Collectors.toList());
    }

    private Player toDomain(ExternalPlayerDto dto) {
//        return Player.builder()
//            .externalId(dto.getId())
//            .firstName(dto.getFirstName())
//            .lastName(dto.getLastName())
//            .position(dto.getPosition())
//            .clubId(dto.getClubId())
//            .cost(dto.getCost())
//            .photoUrl(dto.getPhotoUrl())
//            .active(dto.isActive())
//            .build();

        return null;
    }
}
