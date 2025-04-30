package com.app.domain.dto;

public record HistorialDto(
        Long id,
        String fecha,
        String endpoint,
        String parametros,
        String respuesta,
        String error
) {}
