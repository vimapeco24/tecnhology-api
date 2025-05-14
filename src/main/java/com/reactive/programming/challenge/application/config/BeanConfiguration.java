package com.reactive.programming.challenge.application.config;

import com.reactive.programming.challenge.domain.api.ITechnologyServicePort;
import com.reactive.programming.challenge.domain.spi.ITechnologyPersistencePort;
import com.reactive.programming.challenge.domain.usecase.TechnologyUseCase;
import com.reactive.programming.challenge.infrastructure.adapter.TechnologyAdapter;
import com.reactive.programming.challenge.infrastructure.adapter.mapper.TechnologyEntityMapper;
import com.reactive.programming.challenge.infrastructure.adapter.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final TechnologyRepository technologyRepository;
    private final TechnologyEntityMapper technologyEntityMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort(ITechnologyPersistencePort technologyPersistencePort) {
        return new TechnologyUseCase(technologyPersistencePort);
    }
}
