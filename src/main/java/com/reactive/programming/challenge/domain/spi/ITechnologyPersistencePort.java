package com.reactive.programming.challenge.domain.spi;

import com.reactive.programming.challenge.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import com.reactive.programming.challenge.domain.model.Technology;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ITechnologyPersistencePort {
    Mono<Technology> createTechnology(Technology technology);
    Mono<Technology> getTechnology(Long id);
    Mono<Void> deleteTechnology(Long id);  // <-- Cambiado de deleteCapacity a deleteTechnology
    Flux<Technology> findByIdIn(List<Long> ids);
    Mono<Boolean> existsByName(String name);
}
