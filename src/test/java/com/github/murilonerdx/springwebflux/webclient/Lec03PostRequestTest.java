package com.github.murilonerdx.springwebflux.webclient;

import com.github.murilonerdx.springwebflux.BaseTest;
import com.github.murilonerdx.springwebflux.dto.MultiplyRequestDTO;
import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec03PostRequestTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void postTest() {
        Mono<ResponseDTO> responseDTOMono = webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDTO(5, 2))
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseDTOMono)
                .expectNextCount(1)
                .verifyComplete();
    }


    private MultiplyRequestDTO buildRequestDTO(int a, int b) {
        MultiplyRequestDTO dto = new MultiplyRequestDTO();

        dto.setFirst(a);
        dto.setSecond(b);
        return dto;
    }

}
