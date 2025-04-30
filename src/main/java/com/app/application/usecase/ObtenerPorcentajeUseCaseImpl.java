package com.app.application.usecase;

import com.app.domain.port.in.ObtenerPorcentajeUseCase;
import com.app.domain.port.out.HistorialRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObtenerPorcentajeUseCaseImpl implements ObtenerPorcentajeUseCase {

    private final HistorialRepositoryPort historialRepositoryPort;

    @Override
    public double obtenerPorcentaje() {
        return historialRepositoryPort.obtenerPorcentaje();
    }
}
