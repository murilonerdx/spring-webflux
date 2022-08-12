package com.github.murilonerdx.springwebflux.webclient;

import com.github.murilonerdx.springwebflux.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.Map;

public class Lec07QueryParamsTest extends BaseTest {

    @Autowired
    private WebClient webClient;


    String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";


    @Test
    public void queryParamsTest(){

        Map<String, Integer> map = Map.of(
                "count", 10,
                "page", 20
        );

//        URI uri = UriComponentsBuilder.fromUriString(queryString)
//                .build(10,20);

        Flux<Integer> integerFlux = webClient
                .get()
//                .uri(uri)
//                .uri(u -> u.path("jobs/search").query("count={count}&page={page}").build(10,20))
                .uri(u -> u.path("jobs/search").query("count={count}&page={page}").build(map))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
}
