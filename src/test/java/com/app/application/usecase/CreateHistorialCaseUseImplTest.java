package com.app.application.usecase;

import com.app.domain.model.HistorialModel;
import com.app.domain.port.out.HistorialRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


class CreateHistorialCaseUseImplTest {

    @Mock
    private HistorialRepositoryPort historialRepositoryPort;

    @InjectMocks
    private CreateHistorialCaseUseImpl createHistorialCaseUse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardarHistorial_deberiaInvocarRepositorio() {
        HistorialModel historial = new HistorialModel(
                1L,
                LocalDateTime.now(),
                "/api/calculo",
                "num1=100.0, num2=44.0",
                "resultado=158.4",
                null
        );

        createHistorialCaseUse.guardarHistorial(historial);

        verify(historialRepositoryPort, times(1)).guardarHistorial(historial);
    }


}
