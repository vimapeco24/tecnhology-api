package com.reactive.programming.challenge.infrastructure.entrypoints.handler;

import com.reactive.programming.challenge.domain.enums.TechnicalMessage;
import com.reactive.programming.challenge.infrastructure.entrypoints.util.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyHandler {
    Mono<ServerResponse> createTechnology(ServerRequest request);
    Mono<ServerResponse> getTechnology(ServerRequest request);
    Mono<ServerResponse> deleteTechnology(ServerRequest request);
    Mono<ServerResponse> getTechnologiesByIdIn(ServerRequest request);
    Mono<ServerResponse> buildErrorResponse(HttpStatus httpStatus, String identifier, TechnicalMessage error, List<ErrorDTO> errors);
}
