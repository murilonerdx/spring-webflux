package com.github.murilonerdx.springwebflux.controller;

import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import com.github.murilonerdx.springwebflux.service.ReactiveMathService;
import com.github.murilonerdx.springwebflux.util.SleepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}")
    public Mono<ResponseDTO> findSquare(@PathVariable int input){
        return this.reactiveMathService.findSquare(input);
    }

    @GetMapping(value="table/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ResponseDTO> mutiplicationTable(@PathVariable int input){
        return this.reactiveMathService.multiplicationTable(input);
    }
}
