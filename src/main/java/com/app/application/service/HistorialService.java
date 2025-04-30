package com.app.application.service;

import com.app.domain.model.HistorialModel;
import com.app.domain.port.in.CreateHistorialUseCase;
import com.app.domain.port.in.ListHistorialUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Slf4j
@AllArgsConstructor
@Service
public class HistorialService {
    public final CreateHistorialUseCase createHistorialUseCase;
    public final ListHistorialUseCase listHistorialUseCase;


    @Async("taskExecutor")
    public CompletableFuture<Void> guardarHistorialAsync(HistorialModel historial) {
        log.info("Ejecutando guardarHistorialAsync en hilo: {}", Thread.currentThread().getName());
        createHistorialUseCase.guardarHistorial(historial);
        log.info("Historial guardado correctamente");
        return CompletableFuture.completedFuture(null);
    }

    public Page<HistorialModel> listarHistorial(Pageable pageable) {
        return listHistorialUseCase.listarHistorial(pageable);
    }
}
