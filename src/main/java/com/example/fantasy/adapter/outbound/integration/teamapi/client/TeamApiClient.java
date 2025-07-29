package com.example.fantasy.adapter.outbound.integration.teamapi.client;

import com.example.fantasy.adapter.outbound.integration.teamapi.dto.ExternalTeamDto;
import com.example.fantasy.core.exception.ExternalApiException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.function.Supplier;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TeamApiClient {
    @Qualifier("teamWebClient")
    private final WebClient webClient;
    
    private final CircuitBreaker teamApiCircuitBreaker;
    private final Retry teamApiRetry;

    @Value("${external.team.api.key:}")
    private String apiKey;

    public Optional<ExternalTeamDto> getTeamById(String teamId) {
        Supplier<Optional<ExternalTeamDto>> teamSupplier = () -> {
            try {
                ExternalTeamDto dto = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/v1/teams/{positionId}")
                        .build(teamId))
                    .headers(h -> h.setBearerAuth(apiKey))
                    .retrieve()
                    .bodyToMono(ExternalTeamDto.class)
                    .block();
                return Optional.ofNullable(dto);
            } catch (WebClientResponseException.NotFound e) {
                return Optional.empty();
            } catch (Exception e) {
                throw new ExternalApiException("Error calling Team API", e);
            }
        };
        
        return Retry.decorateSupplier(teamApiRetry, 
                CircuitBreaker.decorateSupplier(teamApiCircuitBreaker, teamSupplier)).get();
    }
}
