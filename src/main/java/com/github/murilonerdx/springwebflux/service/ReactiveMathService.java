package com.github.murilonerdx.springwebflux.service;

import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import com.github.murilonerdx.springwebflux.util.SleepUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ReactiveMathService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MathService.class);

    public Mono<ResponseDTO> findSquare(int input) {
        return Mono.fromSupplier(() -> input * input)
                .map(ResponseDTO::new);
    }

    public Flux<ResponseDTO> multiplicationTable(int input) {
        return Flux.range(1, 10)
                .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> LOGGER.info("math-service procesing : " + i))
                .map(i -> new ResponseDTO(i * input));
    }
}
