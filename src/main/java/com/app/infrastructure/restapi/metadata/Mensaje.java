package com.app.infrastructure.restapi.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Mateo Huancho
 * @version 1.0
 * @since 2025-04-28
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Mensaje {
  /**
   * Código del mensaje, utilizado para identificar el tipo de mensaje.
   */
  String codigo;

  /**
   * Tipo del mensaje (por ejemplo, "ERROR", "INFO", etc.).
   */
  String tipo;

  /**
   * Contenido del mensaje.
   */
  String message;
}
