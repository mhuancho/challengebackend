package com.app.infrastructure.restapi.controller;

import com.app.application.service.HistorialService;
import com.app.application.service.PorcentajeService;
import com.app.domain.constants.Constants;
import com.app.domain.dto.CalculoRequestDto;
import com.app.domain.dto.CalculoResponseDto;
import com.app.domain.model.HistorialModel;
import com.app.infrastructure.restapi.metadata.ApiResponse;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeastOnce;
import java.util.concurrent.TimeUnit;
import com.app.infrastructure.restapi.metadata.ApiResponseMeta;
import com.app.infrastructure.restapi.metadata.Mensaje;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CalculoControllerTest {

    @InjectMocks
    private CalculoController calculoController;

    @Mock
    private PorcentajeService porcentajeService;

    @Mock
    private HistorialService historialService;

    @Test
    void calcular_deberiaRetornarRespuestaCorrecta() {
        CalculoRequestDto request = new CalculoRequestDto(100.0, 50.0);
        when(porcentajeService.obtenerPorcentaje()).thenReturn(10.0);
        ResponseEntity<ApiResponse> responseEntity = calculoController.calcular(request);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        ApiResponse apiResponse = responseEntity.getBody();
        assertNotNull(apiResponse);
        ApiResponseMeta meta = apiResponse.getMeta();
        assertNotNull(meta);
        assertEquals("OK", meta.getResult());
        assertEquals(1, meta.getCantidadRegistros());
        assertNull(meta.getCantidadRegistrosTotal());
        assertFalse(meta.getMensajes().isEmpty());

        Mensaje mensaje = meta.getMensajes().get(0);
        assertEquals(Constants.RESULTADO_OK, mensaje.getCodigo());
        assertEquals(Constants.RESULTADO_CREATE, mensaje.getTipo());
        assertEquals(Constants.RESULTADO_MENSAJE, mensaje.getMessage());
        assertTrue(apiResponse.getData() instanceof CalculoResponseDto);
        CalculoResponseDto data = (CalculoResponseDto) apiResponse.getData();
        assertEquals(165.0, data.resultado(), 0.001);
    }

    @Test
    void calcular_cuandoServicioPorcentajeFalla_lanzaExcepcion() {
        CalculoRequestDto request = new CalculoRequestDto(10.0, 20.0);

        when(porcentajeService.obtenerPorcentaje())
                .thenThrow(new RuntimeException("Servicio no disponible"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            calculoController.calcular(request);
        });

        assertEquals("Servicio no disponible", exception.getMessage());
    }

    @Test
    void calcular_deberiaGuardarHistorial() {
        CalculoRequestDto request = new CalculoRequestDto(10.0, 20.0);

        when(porcentajeService.obtenerPorcentaje()).thenReturn(5.0); // suma=30, +5% = 31.5

        calculoController.calcular(request);

        Awaitility.await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(historialService, atLeastOnce()).guardarHistorialAsync(any(HistorialModel.class));
        });
    }
}
