package com.reactive.programming.challenge.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TechnicalMessage {

    INTERNAL_ERROR("500", "Something went wrong, please try again", ""),
    INTERNAL_ERROR_IN_ADAPTERS("PRC501", "Something went wrong in adapters, please try again", ""),
    INVALID_REQUEST("400", "Bad Request, please verify data", ""),
    INVALID_PARAMETERS("400", "Bad Parameters, please verify data", ""), // Reemplazado por el código "400"
    INVALID_MESSAGE_ID("404", "Invalid Message ID, please verify", "messageId"),
    UNSUPPORTED_OPERATION("501", "Method not supported, please try again", ""),
    TECHNOLOGY_CREATED("201", "Technology created successfully", ""),
    ADAPTER_RESPONSE_NOT_FOUND("404-0", "invalid email, please verify", ""),
    TECHNOLOGY_ALREADY_EXISTS("400", "The technology with the name provided already exists.", ""),
    NOT_ONLY_NUMBERS("400", "Technology name cannot contain only numbers", ""),
    DESCRIPTION_CHARACTER_LIMIT("400", "Technology description must be less than 90 characters", ""),
    NAME_CHARACTER_LIMIT("400", "Technology name must be less than 50 characters", ""),
    UNKNOWN_ERROR("500", "An unknown error occurred, please try again later", ""),
    TECHNOLOGY_CREATION_FAILED("500", "Failed to create the technology, please try again later", ""),
    TECHNOLOGY_NOT_FOUND("404", "Technology not found with the provided ID", ""),
    TECHNOLOGY_DELETION_FAILED("500", "Failed to delete the technology, please try again later", "");

    private final String code;
    private final String message;
    private final String param;
}
