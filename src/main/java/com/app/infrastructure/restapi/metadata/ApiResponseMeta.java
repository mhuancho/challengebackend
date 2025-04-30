package com.app.infrastructure.restapi.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Mateo Huancho
 * @version 1.0
 * @since 2025-04-28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponseMeta {

  /**
   * Resultado de la operación.
   */
  @JsonProperty("result")
  private String result;

  /**
   * Cantidad de registros devueltos.
   */
  @JsonProperty("cantidadRegistros")
  private Integer cantidadRegistros;

  /**
   * Cantidad de registros total.
   */
  @JsonProperty("cantidadRegistrosTotal")
  private Integer cantidadRegistrosTotal;

  /**
   * Número total de páginas disponibles.
   */
  @JsonProperty("totalPages")
  private Integer totalPages;

  /**
   * Número de la página actual.
   */
  @JsonProperty("currentPage")
  private Integer currentPage;

  /**
   * Número de elementos por página.
   */
  @JsonProperty("pageSize")
  private Integer pageSize;

  /**
   * Lista de mensajes asociados con la respuesta.
   */
  @JsonProperty("mensajes")
  private List<Mensaje> mensajes;

  /**
   * Atributos adicionales asociados con la respuesta.
   */
  @JsonProperty("atributos")
  private Map<String, Object> atributos;
}

