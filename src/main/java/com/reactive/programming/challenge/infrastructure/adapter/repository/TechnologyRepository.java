package com.reactive.programming.challenge.infrastructure.adapter.repository;

import com.reactive.programming.challenge.infrastructure.adapter.entity.TechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface TechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, Long> {

    // Buscar por nombre exacto
    Mono<TechnologyEntity> findByName(String name);

    // Buscar múltiples tecnologías por lista de IDs
    Flux<TechnologyEntity> findByIdIn(Collection<Long> ids);
}
