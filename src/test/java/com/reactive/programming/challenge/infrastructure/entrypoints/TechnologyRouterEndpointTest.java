package com.reactive.programming.challenge.infrastructure.entrypoints;

import com.reactive.programming.challenge.domain.enums.TechnicalMessage;
import com.reactive.programming.challenge.domain.model.Technology;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TechnologyRouterEndpointTest {
    @Autowired
    private WebTestClient webTestClient;

    private Technology technologySaved;

    @Test
    @Order(0)
    public void testCreateTechnology() {
        webTestClient.post()
                .uri("/technologies/api/technology")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Technology("MongoDB", "Base de datos NoSQL orientada a documentos para manejar datos no estructurados.")))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class)
                .value(body -> assertEquals(TechnicalMessage.TECHNOLOGY_CREATED.getMessage(), body));


    }

}