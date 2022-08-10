package com.github.murilonerdx.springwebflux.service;

import com.github.murilonerdx.springwebflux.dto.MultiplyRequestDTO;
import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import com.github.murilonerdx.springwebflux.exceptions.InputValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class ReactiveMathService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MathService.class);

    public Mono<ResponseDTO> findSquare(int input) {
        validationInputCause(input);

        return Mono.fromSupplier(() -> input * input)
                .map(ResponseDTO::new);
    }

    public Flux<ResponseDTO> multiplicationTable(int input) {
        validationInputCause(input);

        return Flux.range(1, 10)
//                .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> LOGGER.info("math-service procesing : " + i))
                .map(i -> new ResponseDTO(i * input));
    }

    public Mono<ResponseDTO> multiply(Mono<MultiplyRequestDTO> dtoMono){
        return dtoMono
                .map(dto -> dto.getFirst() * dto.getFirst())
                .map(ResponseDTO::new);
    }

    public void validationInputCause(int input){
        if(input < 10 || input > 20){
            throw new InputValidationException(input);
        }
    }
}
