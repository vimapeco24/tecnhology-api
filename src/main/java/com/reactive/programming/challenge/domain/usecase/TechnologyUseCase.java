package com.reactive.programming.challenge.domain.usecase;

import com.reactive.programming.challenge.domain.api.ITechnologyServicePort;
import com.reactive.programming.challenge.domain.enums.TechnicalMessage;
import com.reactive.programming.challenge.domain.exceptions.BusinessException;
import com.reactive.programming.challenge.domain.model.Technology;
import com.reactive.programming.challenge.domain.spi.ITechnologyPersistencePort;
import com.reactive.programming.challenge.domain.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {

    private static final Logger log = LoggerFactory.getLogger(TechnologyUseCase.class);

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        if (technologyPersistencePort == null) {
            throw new IllegalArgumentException("TechnologyPersistencePort cannot be null");
        }
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public Mono<Technology> registerTechnology(Technology technology) {
        log.info("Registering technology: {}", technology.getName());

        new Validator().validateTechnology(technology);

        return technologyPersistencePort.existsByName(technology.getName())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new BusinessException(TechnicalMessage.TECHNOLOGY_ALREADY_EXISTS));
                    }
                    // Si no existe, intentar crearla
                    return technologyPersistencePort.createTechnology(technology)
                            .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.TECHNOLOGY_CREATION_FAILED)));
                })
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.UNKNOWN_ERROR)));
    }

    @Override
    public Mono<Technology> getTechnology(Long id) {
        log.info("Searching technology with id: {}", id);

        // Obtener la tecnología por ID
        return technologyPersistencePort.getTechnology(id)
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.TECHNOLOGY_NOT_FOUND)));
    }

    @Override
    public Mono<Void> deleteTechnology(Long id) {
        log.info("Deleting technology with id: {}", id);

        // Eliminar la tecnología por ID
        return technologyPersistencePort.deleteTechnology(id)
                .onErrorResume(e -> {
                    log.error("Error deleting technology with id: {}", id, e);
                    return Mono.error(new BusinessException(TechnicalMessage.TECHNOLOGY_DELETION_FAILED));
                });
    }

    @Override
    public Flux<Technology> getTechnologiesByIdIn(List<Long> ids) {
        log.info("Getting technologies with ids: {}", ids);

        // Obtener tecnologías por lista de IDs
        return technologyPersistencePort.findByIdIn(ids)
                .switchIfEmpty(Flux.error(new BusinessException(TechnicalMessage.TECHNOLOGY_NOT_FOUND)));
    }
}
