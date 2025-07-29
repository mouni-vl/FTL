package com.example.fantasy.adapter.outbound.integration.fixtureapi.client;

import com.example.fantasy.adapter.outbound.integration.fixtureapi.dto.ExternalFixtureDto;
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

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FixtureApiClient {
    @Qualifier("fixtureWebClient")
    private final WebClient webClient;
    
    private final CircuitBreaker fixtureApiCircuitBreaker;
    private final Retry fixtureApiRetry;

    @Value("${external.fixture.api.key:}")
    private String apiKey;

    public List<ExternalFixtureDto> getFixturesBySeasonAndGameweek(long seasonId, int gameweekNumber) {
        Supplier<List<ExternalFixtureDto>> fixtureSupplier = () -> {
            try {
                return webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/v1/fixtures")
                        .queryParam("season", seasonId)
                        .queryParam("gameweek", gameweekNumber)
                        .build())
                    .headers(h -> h.setBearerAuth(apiKey))
                    .retrieve()
                    .bodyToFlux(ExternalFixtureDto.class)
                    .collectList()
                    .block();
            } catch (WebClientResponseException e) {
                throw new ExternalApiException("Fixture API returned error: " + e.getResponseBodyAsString(), e);
            } catch (Exception e) {
                throw new ExternalApiException("Error calling Fixture API", e);
            }
        };
        
        return Retry.decorateSupplier(fixtureApiRetry, 
                CircuitBreaker.decorateSupplier(fixtureApiCircuitBreaker, fixtureSupplier)).get();
    }

    public List<ExternalFixtureDto> getFixturesByDateRange(LocalDate from, LocalDate to) {
        Supplier<List<ExternalFixtureDto>> fixtureSupplier = () -> {
            try {
                return webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/v1/fixtures/daterange")
                        .queryParam("from", from.toString())
                        .queryParam("to", to.toString())
                        .build())
                    .headers(h -> h.setBearerAuth(apiKey))
                    .retrieve()
                    .bodyToFlux(ExternalFixtureDto.class)
                    .collectList()
                    .block();
            } catch (WebClientResponseException e) {
                throw new ExternalApiException("Fixture API error (date range): " + e.getResponseBodyAsString(), e);
            } catch (Exception e) {
                throw new ExternalApiException("Error calling Fixture API (date range)", e);
            }
        };
        
        return Retry.decorateSupplier(fixtureApiRetry, 
                CircuitBreaker.decorateSupplier(fixtureApiCircuitBreaker, fixtureSupplier)).get();
    }
}
