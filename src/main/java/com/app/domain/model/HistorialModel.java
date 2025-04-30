package com.app.domain.model;

import java.time.LocalDateTime;

public record HistorialModel(
        Long id,
        LocalDateTime fecha,
        String endpoint,
        String parametros,
        String respuesta,
        String error
) {}
