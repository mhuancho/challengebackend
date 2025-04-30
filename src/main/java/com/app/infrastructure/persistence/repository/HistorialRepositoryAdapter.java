package com.app.infrastructure.persistence.repository;

import com.app.domain.config.PorcentajeFeignClient;
import com.app.domain.model.HistorialModel;
import com.app.domain.port.out.HistorialRepositoryPort;
import com.app.infrastructure.persistence.entity.Historial;
import com.app.infrastructure.restapi.mapper.HistoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class HistorialRepositoryAdapter implements HistorialRepositoryPort {

    private final HistorialJpaRepository historialJpaRepository;
    private final HistoryMapper historialMapper;
    private final PorcentajeFeignClient porcentajeFeignClient;


    @Override
    public void guardarHistorial(HistorialModel historial) {
        Historial entity = historialMapper.toEntity(historial);
        historialJpaRepository.save(entity);
    }

    public Page<HistorialModel> listarHistorial(Pageable pageable) {
        return historialJpaRepository.findAll(pageable)
                .map(historialMapper::toModel);
    }


    @Override
    public double obtenerPorcentaje() {
        return porcentajeFeignClient.getPorcentaje();
    }
}
