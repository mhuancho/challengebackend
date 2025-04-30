package com.app.infrastructure.persistence.repository;

import com.app.domain.config.PorcentajeFeignClient;
import com.app.domain.model.HistorialModel;
import com.app.infrastructure.persistence.entity.Historial;
import com.app.infrastructure.restapi.mapper.HistoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class HistorialRepositoryAdapterTest {

    @Mock
    private HistorialJpaRepository historialJpaRepository;

    @Mock
    private HistoryMapper historialMapper;

    @Mock
    private PorcentajeFeignClient porcentajeFeignClient;

    @InjectMocks
    private HistorialRepositoryAdapter historialRepositoryAdapter;

    private HistorialModel historialModel;
    private Historial historialEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        LocalDateTime fecha = LocalDateTime.parse("2023-12-12T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        historialModel = new HistorialModel(
                1L,
                fecha,
                "/api/calculo",
                "num1=100.0, num2=44.0",
                "Cálculo realizado",
                null
        );
        historialEntity = new Historial();
        historialEntity.setId(1L);
        historialEntity.setFecha(fecha);
        historialEntity.setEndpoint("/api/calculo");
        historialEntity.setParametros("num1=100.0, num2=44.0");
        historialEntity.setRespuesta("Cálculo realizado");
        historialEntity.setError(null);
    }

    @Test
    void guardarHistorial_deberiaGuardarCorrectamente() {
        when(historialMapper.toEntity(any(HistorialModel.class))).thenReturn(historialEntity);
        historialRepositoryAdapter.guardarHistorial(historialModel);
        verify(historialJpaRepository, times(1)).save(historialEntity);
    }

    @Test
    void listarHistorial_deberiaRetornarListaCorrecta() {
        List<Historial> entities = List.of(historialEntity);
        Page<Historial> pageEntities = new PageImpl<>(entities, PageRequest.of(0, 10), entities.size());
        when(historialJpaRepository.findAll(PageRequest.of(0, 10))).thenReturn(pageEntities);
        when(historialMapper.toModel(historialEntity)).thenReturn(historialModel);
        Page<HistorialModel> result = historialRepositoryAdapter.listarHistorial(PageRequest.of(0, 10));
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(historialModel, result.getContent().get(0));
    }

    @Test
    void obtenerPorcentaje_deberiaRetornarPorcentajeCorrecto() {
        double porcentajeEsperado = 10.0;
        when(porcentajeFeignClient.getPorcentaje()).thenReturn(porcentajeEsperado);
        double porcentajeObtenido = historialRepositoryAdapter.obtenerPorcentaje();
        assertEquals(porcentajeEsperado, porcentajeObtenido, 0.01);
    }
}
