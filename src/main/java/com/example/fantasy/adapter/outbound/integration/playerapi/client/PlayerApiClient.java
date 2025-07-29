package com.example.fantasy.adapter.outbound.integration.playerapi.client;

import com.example.fantasy.adapter.outbound.integration.playerapi.dto.ExternalPlayerDto;
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

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerApiClient {
    @Qualifier("playerWebClient")
    private final WebClient webClient;
    
    private final CircuitBreaker playerApiCircuitBreaker;
    private final Retry playerApiRetry;

    @Value("${external.player.api.key:}")
    private String apiKey;

    public Optional<ExternalPlayerDto> getPlayerById(String playerId) {
        Supplier<Optional<ExternalPlayerDto>> playerSupplier = () -> {
            try {
                ExternalPlayerDto dto = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/v1/players/{positionId}")
                        .build(playerId))
                    .headers(h -> h.setBearerAuth(apiKey))
                    .retrieve()
                    .bodyToMono(ExternalPlayerDto.class)
                    .block();
                return Optional.ofNullable(dto);
            } catch (WebClientResponseException.NotFound e) {
                return Optional.empty();
            } catch (Exception e) {
                throw new ExternalApiException("Error calling Player API", e);
            }
        };
        
        return Retry.decorateSupplier(playerApiRetry, 
                CircuitBreaker.decorateSupplier(playerApiCircuitBreaker, playerSupplier)).get();
    }

    public List<ExternalPlayerDto> getPlayersByClub(String clubId) {
        Supplier<List<ExternalPlayerDto>> playersSupplier = () -> {
            try {
                return webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/v1/players")
                        .queryParam("club", clubId)
                        .build())
                    .headers(h -> h.setBearerAuth(apiKey))
                    .retrieve()
                    .bodyToFlux(ExternalPlayerDto.class)
                    .collectList()
                    .block();
            } catch (WebClientResponseException e) {
                throw new ExternalApiException("Player API error: " + e.getResponseBodyAsString(), e);
            } catch (Exception e) {
                throw new ExternalApiException("Error calling Player API", e);
            }
        };
        
        return Retry.decorateSupplier(playerApiRetry, 
                CircuitBreaker.decorateSupplier(playerApiCircuitBreaker, playersSupplier)).get();
    }
}
