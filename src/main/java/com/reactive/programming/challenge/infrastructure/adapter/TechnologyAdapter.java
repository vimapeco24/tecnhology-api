package com.reactive.programming.challenge.infrastructure.adapter;

import com.reactive.programming.challenge.domain.model.Technology;
import com.reactive.programming.challenge.domain.spi.ITechnologyPersistencePort;
import com.reactive.programming.challenge.infrastructure.adapter.mapper.TechnologyEntityMapper;
import com.reactive.programming.challenge.infrastructure.adapter.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final TechnologyRepository technologyRepository;
    private final TechnologyEntityMapper technologyEntityMapper;

    @Override
    public Mono<Technology> createTechnology(Technology technology) {
        return technologyRepository.save(technologyEntityMapper.toEntity(technology))
                .map(technologyEntityMapper::toTechnology);
    }

    @Override
    public Mono<Technology> getTechnology(Long id) {
        return technologyRepository.findById(id)
                .map(technologyEntityMapper::toTechnology);
    }

    @Override
    public Mono<Void> deleteTechnology(Long id) {
        return technologyRepository.deleteById(id);
    }

    @Override
    public Flux<Technology> findByIdIn(List<Long> ids) {
        return technologyRepository.findByIdIn(ids)
                .map(technologyEntityMapper::toTechnology);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return technologyRepository.findByName(name)
                .map(tech -> true)
                .defaultIfEmpty(false);
    }
}
