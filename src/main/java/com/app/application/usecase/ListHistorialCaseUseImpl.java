package com.app.application.usecase;

import com.app.domain.model.HistorialModel;
import com.app.domain.port.in.ListHistorialUseCase;
import com.app.domain.port.out.HistorialRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListHistorialCaseUseImpl implements ListHistorialUseCase {

    private final HistorialRepositoryPort historialRepositoryPort;

    @Override
    public Page<HistorialModel> listarHistorial(Pageable pageable) {
        return historialRepositoryPort.listarHistorial(pageable);
    }
}
