package com.reactive.programming.challenge.infrastructure.adapter.repository;

import com.reactive.programming.challenge.infrastructure.adapter.entity.CapabilityEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface CapabilityRepository extends ReactiveCrudRepository<CapabilityEntity, Long> {

    // Buscar una capacidad por nombre exacto
    Mono<CapabilityEntity> findByName(String name);

    // Buscar varias capacidades por una lista de IDs
    Flux<CapabilityEntity> findByIdIn(Collection<Long> ids);

    // Buscar capacidades cuya descripción contenga un fragmento de texto (opcional)
    Flux<CapabilityEntity> findByDescriptionContainingIgnoreCase(String partialDescription);
}