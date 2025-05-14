package com.reactive.programming.challenge.infrastructure.entrypoints.mapper;

import com.reactive.programming.challenge.domain.model.Technology;
import com.reactive.programming.challenge.infrastructure.entrypoints.dto.TechnologyDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T09:04:41-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class TechnologyMapperImpl implements TechnologyMapper {

    @Override
    public Technology technologyDTOToTechnology(TechnologyDTO technologyDTO) {
        if ( technologyDTO == null ) {
            return null;
        }

        Technology technology = new Technology();

        technology.setName( technologyDTO.getName() );
        technology.setDescription( technologyDTO.getDescription() );

        return technology;
    }
}
