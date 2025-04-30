package com.app.application.usecase;

import com.app.domain.model.HistorialModel;
import com.app.domain.port.in.CreateHistorialUseCase;
import com.app.domain.port.out.HistorialRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CreateHistorialCaseUseImpl implements CreateHistorialUseCase {

    private final HistorialRepositoryPort historialRepositoryPort;

    @Override
    public void guardarHistorial(HistorialModel historial) {
        historialRepositoryPort.guardarHistorial(historial);
    }
}
