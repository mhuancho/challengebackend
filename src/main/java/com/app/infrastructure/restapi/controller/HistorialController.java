package com.app.infrastructure.restapi.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.app.application.service.HistorialService;
import com.app.domain.dto.HistorialDto;
import com.app.domain.model.HistorialModel;
import com.app.infrastructure.restapi.mapper.HistoryMapper;
import com.app.infrastructure.restapi.metadata.ApiResponse;
import com.app.infrastructure.restapi.metadata.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import static com.app.domain.constants.Constants.RESULTADO_OK;
import static com.app.domain.constants.Constants.RESULTADO_LISTAR;
import static com.app.domain.constants.Constants.RESULTADO_MENSAJE_LISTAR;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
public class HistorialController {

    private final HistorialService historialService;
    private final HistoryMapper historyMapper;

    @GetMapping
    public CompletableFuture<ResponseEntity<ApiResponse>> listarHistorial(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return CompletableFuture.supplyAsync(() -> {
            Page<HistorialModel> historialPage = historialService.listarHistorial(pageable);
            List<HistorialDto> historialDtos = historyMapper.toDtoList(historialPage.getContent());
            return ResponseUtils.buildResponse(
                    historialDtos,
                    historialPage,
                    RESULTADO_OK,
                    RESULTADO_LISTAR,
                    RESULTADO_MENSAJE_LISTAR,
                    true,
                    HttpStatus.OK
            );
        });
    }


}
