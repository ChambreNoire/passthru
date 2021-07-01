package com.chambrenoire.passthru.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(@Value("${gateway.url}") String gatewayUrl) {
        return WebClient.builder()
            .baseUrl(gatewayUrl)
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                //.wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)
                .followRedirect(true)))
            .build();
    }
}
