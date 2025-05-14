package com.reactive.programming.challenge.infrastructure.adapter.mapper;

import com.reactive.programming.challenge.domain.model.Technology;
import com.reactive.programming.challenge.infrastructure.adapter.entity.TechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnologyEntityMapper {
    Technology toTechnology(TechnologyEntity technologyEntity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TechnologyEntity toEntity(Technology technology);
}
