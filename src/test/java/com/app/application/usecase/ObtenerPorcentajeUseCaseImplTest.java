package com.app.application.usecase;

import com.app.domain.port.out.HistorialRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


class ObtenerPorcentajeUseCaseImplTest {

    @Mock
    private HistorialRepositoryPort historialRepositoryPort;

    @InjectMocks
    private ObtenerPorcentajeUseCaseImpl obtenerPorcentajeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerPorcentaje_deberiaRetornarValorCorrecto() {
        double porcentajeEsperado = 10.0;
        when(historialRepositoryPort.obtenerPorcentaje()).thenReturn(porcentajeEsperado);
        double porcentajeObtenido = obtenerPorcentajeUseCase.obtenerPorcentaje();
        assertEquals(porcentajeEsperado, porcentajeObtenido);
        verify(historialRepositoryPort, times(1)).obtenerPorcentaje();  // Verifica que se llamó al método correctamente
    }


}
