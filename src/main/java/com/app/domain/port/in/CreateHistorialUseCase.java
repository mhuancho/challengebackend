package com.app.domain.port.in;

import com.app.domain.model.HistorialModel;
import org.springframework.stereotype.Component;

@Component
public interface CreateHistorialUseCase {
    void guardarHistorial(HistorialModel historial);
}
