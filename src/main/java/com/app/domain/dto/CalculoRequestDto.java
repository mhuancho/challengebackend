package com.app.domain.dto;

import jakarta.validation.constraints.NotNull;

public record CalculoRequestDto(
        @NotNull(message = "num1 es obligatorio") Double num1,
        @NotNull(message = "num2 es obligatorio") Double num2
) {}
