package com.reactive.programming.challenge.domain.usecase;

import com.reactive.programming.challenge.domain.exceptions.BusinessException;
import com.reactive.programming.challenge.domain.model.Technology;
import com.reactive.programming.challenge.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TechnologyUseCaseTest {

    private ITechnologyPersistencePort technologyPersistencePort;
    private TechnologyUseCase technologyUseCase;

    private Technology technology1;
    private Technology technology2;
    private Technology technology3;
    private Technology technology4;

    @BeforeAll
    public void setup() {
        technologyPersistencePort = mock(ITechnologyPersistencePort.class);
        technologyUseCase = new TechnologyUseCase(technologyPersistencePort);

        technology1 = new Technology("Spring Boot", "Framework de Java que simplifica la creación de aplicaciones basadas en Spring, permitiendo una configuración mínima y una rápida puesta en marcha.");
        technology2 = new Technology("React.......................................................................", "Biblioteca de JavaScript para construir interfaces de usuario interactivas.");
        technology3 = new Technology("624231123", "Servicios en la nube de Amazon de computación, almacenamiento, bases de datos, etc.");
        technology4 = new Technology("HTML", "Lenguaje de marcado estándar para crear y estructurar páginas web y aplicaciones web.");
    }

    @Test
    @Order(1)
    public void createTechnologyWithInvalidDescriptionShouldThrowBadRequest() {
        BusinessException exception = assertThrows(BusinessException.class,
                () -> technologyUseCase.registerTechnology(technology1).block());

        assertEquals("Technology description must be less than 90 characters",
                exception.getTechnicalMessage().getMessage());
    }

    @Test
    @Order(2)
    public void createTechnologyWithInvalidNameShouldThrowBadRequest() {
        BusinessException exception = assertThrows(BusinessException.class,
                () -> technologyUseCase.registerTechnology(technology2).block());

        assertEquals("Technology name must be less than 50 characters",
                exception.getTechnicalMessage().getMessage());
    }

    @Test
    @Order(3)
    public void createTechnologyWithOnlyNumbersInNameShouldThrowBadRequest() {
        BusinessException exception = assertThrows(BusinessException.class,
                () -> technologyUseCase.registerTechnology(technology3).block());

        assertEquals("Technology name cannot contain only numbers",
                exception.getTechnicalMessage().getMessage());
    }

    @Test
    @Order(4)
    public void createTechnologyWithValidDataShouldReturnTechnology() {
        // Arrange
        when(technologyPersistencePort.existsByName(technology4.getName()))
                .thenReturn(Mono.just(false));

        when(technologyPersistencePort.createTechnology(technology4))
                .thenReturn(Mono.just(technology4));

        // Act
        Mono<Technology> result = technologyUseCase.registerTechnology(technology4);

        // Assert
        StepVerifier.create(result)
                .expectNext(technology4)
                .verifyComplete();

        verify(technologyPersistencePort).existsByName(technology4.getName());
        verify(technologyPersistencePort).createTechnology(technology4);
    }
}
