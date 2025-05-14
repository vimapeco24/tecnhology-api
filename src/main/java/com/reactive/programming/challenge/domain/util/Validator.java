package com.reactive.programming.challenge.domain.util;


import com.reactive.programming.challenge.domain.model.Technology;
import com.reactive.programming.challenge.domain.exceptions.BusinessException;
import com.reactive.programming.challenge.domain.enums.TechnicalMessage;

public class Validator {

    public void validateTechnology(Technology technology) {
        // Validar si el nombre contiene solo números
        if (technology.getName().matches("[0-9]+")) {
            throw new BusinessException(TechnicalMessage.NOT_ONLY_NUMBERS);
        }

        // Validar el tamaño del nombre
        if (technology.getName().length() > 50) {
            throw new BusinessException(TechnicalMessage.NAME_CHARACTER_LIMIT);
        }

        // Validar el tamaño de la descripción
        if (technology.getDescription().length() > 90) {
            throw new BusinessException(TechnicalMessage.DESCRIPTION_CHARACTER_LIMIT);
        }
    }
}

