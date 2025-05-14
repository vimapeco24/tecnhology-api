package com.reactive.programming.challenge.domain.api;

import com.reactive.programming.challenge.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyServicePort {
    Mono<Technology> registerTechnology(Technology technology);
    Mono<Technology> getTechnology(Long id);
    Mono<Void> deleteTechnology(Long id);
    Flux<Technology> getTechnologiesByIdIn(List<Long> ids);
}
