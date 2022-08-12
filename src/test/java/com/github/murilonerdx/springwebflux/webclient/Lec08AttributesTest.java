package com.github.murilonerdx.springwebflux.webclient;

import com.github.murilonerdx.springwebflux.BaseTest;
import com.github.murilonerdx.springwebflux.dto.MultiplyRequestDTO;
import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec08AttributesTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void headersTest(){
        Mono<ResponseDTO> responseMono = this.webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDto(5, 2))
                .attribute("auth", "oauth")
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();

    }

    private MultiplyRequestDTO buildRequestDto(int a, int b){
        MultiplyRequestDTO dto = new MultiplyRequestDTO();
        dto.setFirst(a);
        dto.setSecond(b);
        return dto;
    }

}
