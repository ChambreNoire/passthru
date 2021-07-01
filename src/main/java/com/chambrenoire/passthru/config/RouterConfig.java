package com.chambrenoire.passthru.config;

import com.chambrenoire.passthru.handler.RouteHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RequiredArgsConstructor
@Configuration
public class RouterConfig {

    private final RouteHandler handler;

    @Bean
    public RouterFunction<ServerResponse> passthru() {
        return route(all(), this.handler::doPassthru);
    }
}
