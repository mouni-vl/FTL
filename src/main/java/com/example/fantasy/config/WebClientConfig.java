package com.example.fantasy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${external.fixture.api.url}")
    private String fixtureApiBaseUrl;

//    @Value("${external.player.api.url}")
//    private String playerApiBaseUrl;
//
//    @Value("${external.team.api.url}")
//    private String teamApiBaseUrl;

    @Bean("fixtureWebClient")
    public WebClient fixtureWebClient(WebClient.Builder builder) {
        return builder.baseUrl(fixtureApiBaseUrl).build();
    }

//    @Bean("playerWebClient")
//    public WebClient playerWebClient(WebClient.Builder builder) {
//        return builder.baseUrl(playerApiBaseUrl).build();
//    }
//
//    @Bean("teamWebClient")
//    public WebClient teamWebClient(WebClient.Builder builder) {
//        return builder.baseUrl(teamApiBaseUrl).build();
//    }
}
