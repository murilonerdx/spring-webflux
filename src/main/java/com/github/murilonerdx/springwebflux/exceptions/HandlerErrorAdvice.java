package com.github.murilonerdx.springwebflux.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerErrorAdvice {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException e){
        InputFailedValidationResponse invalidResponse = new InputFailedValidationResponse();

        invalidResponse.setMessage(e.getMessage());
        invalidResponse.setErrorCode(e.getErrorCode());
        invalidResponse.setInput(e.getInput());

        return ResponseEntity.badRequest().body(invalidResponse);
    }
}
