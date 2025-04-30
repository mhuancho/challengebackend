package com.app.infrastructure.restapi.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mateo Huancho
 * @version 1.0
 * @since 2025-04-28
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ApiResponse {

    /**
     * Metadatos de la respuesta.
     */
    @JsonProperty("meta")
    private ApiResponseMeta meta;

    /**
     * Datos de la respuesta.
     */
    @JsonProperty("data")
    private Object data;

}
