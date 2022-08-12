package com.github.murilonerdx.springwebflux.config;

import com.github.murilonerdx.springwebflux.dto.MultiplyRequestDTO;
import com.github.murilonerdx.springwebflux.dto.ResponseDTO;
import com.github.murilonerdx.springwebflux.exceptions.InputValidationException;
import com.github.murilonerdx.springwebflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

    @Autowired
    private ReactiveMathService mathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<ResponseDTO> responseDTO = this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseDTO, ResponseDTO.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<ResponseDTO> responseFlux = this.mathService.multiplicationTable(input);
        return ServerResponse.ok().body(responseFlux, ResponseDTO.class);
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<ResponseDTO> responseFlux = this.mathService.multiplicationTable(input);
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, ResponseDTO.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest){
        Mono<MultiplyRequestDTO> multiplyRequestDTOMono = serverRequest.bodyToMono(MultiplyRequestDTO.class);
        Mono<ResponseDTO> multiply = mathService.multiply(multiplyRequestDTOMono);

        return ServerResponse
                .ok()
                .body(multiply, ResponseDTO.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if(input < 10  || input > 20)
            return Mono.error(new InputValidationException(input));
        Mono<ResponseDTO> responseMono = this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, ResponseDTO.class);
    }
}
