package com.reactive.programming.challenge.infrastructure.adapter.mapper;

import com.reactive.programming.challenge.domain.model.Technology;
import com.reactive.programming.challenge.infrastructure.adapter.entity.TechnologyEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T09:04:41-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class TechnologyEntityMapperImpl implements TechnologyEntityMapper {

    @Override
    public Technology toTechnology(TechnologyEntity technologyEntity) {
        if ( technologyEntity == null ) {
            return null;
        }

        Technology technology = new Technology();

        technology.setId( technologyEntity.getId() );
        technology.setName( technologyEntity.getName() );
        technology.setDescription( technologyEntity.getDescription() );

        return technology;
    }

    @Override
    public TechnologyEntity toEntity(Technology technology) {
        if ( technology == null ) {
            return null;
        }

        TechnologyEntity technologyEntity = new TechnologyEntity();

        technologyEntity.setId( technology.getId() );
        technologyEntity.setName( technology.getName() );
        technologyEntity.setDescription( technology.getDescription() );

        return technologyEntity;
    }
}
