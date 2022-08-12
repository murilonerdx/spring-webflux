package com.github.murilonerdx.springwebflux.webclient;

import com.github.murilonerdx.springwebflux.BaseTest;
import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01GetSingleResponseTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest(){
        ResponseDTO response = webClient
                .get()
                .uri("reactive-math/square/{number}", 5)
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .block();
    }

    @Test
    public void stepVerifierTest(){
        Mono<ResponseDTO> response = webClient
                .get()
                .uri("reactive-math/square/{number}", 5)
                .retrieve()
                .bodyToMono(ResponseDTO.class);

        StepVerifier.create(response)
                .expectNextMatches(r-> r.getOutput() == 25)
                .verifyComplete();
    }
}
