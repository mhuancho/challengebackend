package com.app.infrastructure.restapi.mapper;

import com.app.domain.dto.HistorialDto;
import com.app.domain.model.HistorialModel;
import com.app.infrastructure.persistence.entity.Historial;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HistoryMapper {

    HistorialModel toModel(Historial entity);
    Historial toEntity(HistorialModel model);
    HistorialDto toDto(HistorialModel model);

    List<HistorialDto> toDtoList(List<HistorialModel> models);
}
