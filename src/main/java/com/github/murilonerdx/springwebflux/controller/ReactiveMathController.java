package com.github.murilonerdx.springwebflux.controller;

import com.github.murilonerdx.springwebflux.dto.MultiplyRequestDTO;
import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import com.github.murilonerdx.springwebflux.exceptions.InputValidationException;
import com.github.murilonerdx.springwebflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}")
    public Mono<ResponseDTO> findSquare(@PathVariable int input){
        return this.reactiveMathService.findSquare(input);
    }

    @GetMapping(value="table/{input}")
    public Flux<ResponseDTO> mutiplicationTable(@PathVariable int input){
        return this.reactiveMathService.multiplicationTable(input);
    }

    @GetMapping(value="table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ResponseDTO> mutiplicationTableStream(@PathVariable int input){
        return this.reactiveMathService.multiplicationTable(input);
    }

    @PostMapping("multiply")
    public Mono<ResponseDTO> multiply(@RequestBody Mono<MultiplyRequestDTO> multiplyRequestDTO, @RequestHeader Map<String,String> headers){
        System.out.println(headers);
        return this.reactiveMathService.multiply(multiplyRequestDTO);
    }

    @GetMapping("square/{input}/mono-error")
    public Mono<ResponseDTO> findSquareMonoError(@PathVariable int input){
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if(input < 10 || input > 20)
                       sink.next(integer);
                    else
                        sink.error(new InputValidationException(input));
                })
                .cast(Integer.class)
                .flatMap(i -> this.reactiveMathService.findSquare(input));
    }


    @GetMapping("square/{input}/assignment")
    public Mono<ResponseEntity<ResponseDTO>> findSquareAssignment(@PathVariable int input){
        return Mono.just(input)
                .filter(i -> i>= 10 && i <= 20)
                .flatMap(i -> this.reactiveMathService.findSquare(i))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
