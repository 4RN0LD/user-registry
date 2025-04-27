package com.bci.bcichallenge.controller;

import com.bci.bcichallenge.Exceptions.EmailAlreadyExistsException;
import com.bci.bcichallenge.dto.exception.GlobalExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<GlobalExceptionResponse> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());

        return Mono.just(GlobalExceptionResponse.builder().mensaje(response.toString()).build());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<GlobalExceptionResponse>> handleValidationException(WebExchangeBindException ex) {
        List<String> errors = ex.getFieldErrors().stream()
                .map(fieldError -> "Hubo un problema con el campo " + fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(GlobalExceptionResponse.builder()
                        .mensaje(String.join(".\n", errors))
                        .build()));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public Mono<ResponseEntity<GlobalExceptionResponse>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(GlobalExceptionResponse.builder().mensaje("El correo ya ha sido registrado").build()));
    }
}
