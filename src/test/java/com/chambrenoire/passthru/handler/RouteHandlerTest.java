package com.chambrenoire.passthru.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import com.chambrenoire.passthru.PassthruApplication;
import com.chambrenoire.passthru.config.RouterConfig;
import com.chambrenoire.passthru.model.AccessTokens;
import com.chambrenoire.passthru.model.AuthRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
    classes = PassthruApplication.class,
    webEnvironment = RANDOM_PORT
)
public class RouteHandlerTest {

    @Autowired
    private RouterConfig config;

    @MockBean
    private RouteHandler handler;

    @Test
    public void doWithoutAuth() {

        WebTestClient testClient = WebTestClient
            .bindToRouterFunction(config.passthru())
            .build();

        final AuthRequest authRequest = AuthRequest.builder()
            .secretKey("secretKey")
            .build();

        final AccessTokens accessTokens = AccessTokens.builder()
            .accountId("accountId")
            .accessToken("accessToken")
            .renewToken("renewToken")
            .build();

        final Mono<ServerResponse> response = ServerResponse
            .ok()
            .body(Mono.just(accessTokens), AccessTokens.class);

        when(handler.doPassthru(any(ServerRequest.class))).thenReturn(response);

        testClient
            .post()
            .uri("/authentication/sign-in")
            .body(Mono.just(authRequest), AuthRequest.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody(AccessTokens.class)
            .isEqualTo(accessTokens);
    }
}