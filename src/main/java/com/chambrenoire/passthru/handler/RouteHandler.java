package com.chambrenoire.passthru.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.PooledDataBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class RouteHandler {

    private final WebClient webClient;

    @SuppressWarnings("ConstantConditions")
    public Mono<ServerResponse> doPassthru(ServerRequest request) {
        return webClient.method(request.method())
            .uri(request.path())
            .headers(httpHeaders -> request.headers().asHttpHeaders().forEach(httpHeaders::put))
            .body(request.bodyToMono(PooledDataBuffer.class), PooledDataBuffer.class)
            .exchangeToMono(response -> response.bodyToMono(PooledDataBuffer.class)
                .flatMap(buffer -> ServerResponse
                    .status(response.statusCode())
                    .headers(headers -> headers.addAll(response.headers().asHttpHeaders()))
                    .bodyValue(buffer)));
    }
}
