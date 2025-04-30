package com.app.domain.port.out;

import com.app.domain.model.HistorialModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface HistorialRepositoryPort {
    void guardarHistorial(HistorialModel historial);
    Page<HistorialModel> listarHistorial(Pageable pageable);
    double obtenerPorcentaje();
}
