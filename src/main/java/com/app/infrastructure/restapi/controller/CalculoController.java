package com.app.infrastructure.restapi.controller;

import com.app.application.service.HistorialService;
import com.app.application.service.PorcentajeService;
import com.app.domain.dto.CalculoRequestDto;
import com.app.domain.dto.CalculoResponseDto;
import com.app.domain.model.HistorialModel;
import com.app.infrastructure.restapi.metadata.ApiResponse;
import com.app.infrastructure.restapi.metadata.ResponseUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import static com.app.domain.constants.Constants.ENDPOINT_CALCULO;
import static com.app.domain.constants.Constants.NUM_1;
import static com.app.domain.constants.Constants.NUM_2;
import static com.app.domain.constants.Constants.RESULTADO;
import static com.app.domain.constants.Constants.RESULTADO_OK;
import static com.app.domain.constants.Constants.RESULTADO_CREATE;
import static com.app.domain.constants.Constants.RESULTADO_MENSAJE;

@RestController
@RequestMapping("/api/calculo")
@RequiredArgsConstructor
public class CalculoController {

    private final PorcentajeService porcentajeService;
    private final HistorialService historialService;

    @PostMapping
    public ResponseEntity<ApiResponse> calcular(@Valid @RequestBody CalculoRequestDto request) {
        double porcentaje = porcentajeService.obtenerPorcentaje();
        double suma = request.num1() + request.num2();
        double resultadoFinal = suma + (suma * porcentaje / 100);
        CalculoResponseDto response = new CalculoResponseDto(resultadoFinal);

        HistorialModel historial = new HistorialModel(
                null,
                LocalDateTime.now(),
                ENDPOINT_CALCULO,
                NUM_1 + request.num1() + NUM_2 + request.num2(),
                RESULTADO + resultadoFinal,
                null
        );

        CompletableFuture.runAsync(() -> historialService.guardarHistorialAsync(historial));

        return ResponseUtils.buildResponse(response, RESULTADO_OK, RESULTADO_CREATE,
                RESULTADO_MENSAJE, true, HttpStatus.CREATED);
    }
}
