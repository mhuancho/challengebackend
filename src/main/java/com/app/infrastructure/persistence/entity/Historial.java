package com.app.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Historial {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CAL_SEQ_HISTORIAL")
    @SequenceGenerator(
            name = "CAL_SEQ_HISTORIAL",
            sequenceName = "CAL_SEQ_HISTORIAL",
            initialValue = 1, allocationSize = 1)
    private Long id;

    private LocalDateTime fecha;
    private String endpoint;
    private String parametros;
    private String respuesta;
    private String error;
}
