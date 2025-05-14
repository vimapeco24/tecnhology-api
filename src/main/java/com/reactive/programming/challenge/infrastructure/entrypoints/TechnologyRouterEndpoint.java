package com.reactive.programming.challenge.infrastructure.entrypoints;

import com.reactive.programming.challenge.infrastructure.entrypoints.handler.ITechnologyHandler;
import com.reactive.programming.challenge.domain.model.Technology;
import com.reactive.programming.challenge.infrastructure.adapter.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TechnologyRouterEndpoint {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = Constants.API_TECHNOLOGY,
                    produces = { MediaType.APPLICATION_JSON_VALUE },
                    method = RequestMethod.POST,
                    beanClass = ITechnologyHandler.class,
                    beanMethod = Constants.METHOD_NAME,
                    operation = @Operation(
                            operationId = Constants.METHOD_NAME,
                            summary = Constants.OPERATION_SUMMARY,
                            tags = { Constants.TECHNOLOGIES },
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    description = Constants.REQUEST_BODY_DESCRIPTION,
                                    required = true,
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Technology.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = Constants.OK,
                                            description = Constants.TECHNOLOGY_CREATED,
                                            content = @Content(
                                                    mediaType = MediaType.TEXT_PLAIN_VALUE,
                                                    schema = @Schema(type = Constants.STRING, example = Constants.TECHNOLOGY_CREATED)
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = Constants.BAD_REQUEST_CODE,
                                            description = Constants.BAD_REQUEST_DESCRIPTION,
                                            content = @Content(
                                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(type = Constants.OBJECT, example = Constants.ERROR_RESPONSE)
                                            )
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> technologyRouter(ITechnologyHandler technologyHandler) {
        return RouterFunctions
                .route(POST(Constants.PATH_POST_TECHNOLOGY), technologyHandler::createTechnology)
                .andRoute(GET(Constants.PATH_GET_TECHNOLOGY), technologyHandler::getTechnology)
                .andRoute(DELETE(Constants.PATH_GET_TECHNOLOGY), technologyHandler::deleteTechnology)
                .andRoute(POST(Constants.PATH_GET_TECHNOLOGIES_BY_ID_IN), technologyHandler::getTechnologiesByIdIn);
    }
}
