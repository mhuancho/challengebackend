package com.app.infrastructure.restapi.mapper;

import com.app.domain.dto.CalculoRequestDto;
import com.app.domain.dto.CalculoResponseDto;
import com.app.domain.model.CalculoModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CalculoMapper {

    CalculoModel toModel(CalculoRequestDto dto);
    CalculoResponseDto toDto(CalculoModel model);

}
