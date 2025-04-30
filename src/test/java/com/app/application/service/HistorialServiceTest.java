package com.app.application.service;

import com.app.domain.model.HistorialModel;
import com.app.domain.port.in.CreateHistorialUseCase;
import com.app.domain.port.in.ListHistorialUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.concurrent.CompletableFuture;


@ExtendWith(MockitoExtension.class)
class HistorialServiceTest {

    @Mock
    private CreateHistorialUseCase createHistorialUseCase;

    @Mock
    private ListHistorialUseCase listHistorialUseCase;

    @InjectMocks
    private HistorialService historialService;

    private HistorialModel historialModel;

    @BeforeEach
    void setUp() {
        historialModel = new HistorialModel(
                1L,
                LocalDateTime.now(),
                "/api/calculo",
                "num1=100.0, num2=44.0",
                "resultado=158.4",
                null
        );
    }

    @Test
    void testGuardarHistorialAsync() throws Exception {
        CompletableFuture<Void> result = historialService.guardarHistorialAsync(historialModel);
        verify(createHistorialUseCase, times(1)).guardarHistorial(historialModel);
        assertDoesNotThrow(() -> result.get());
    }

    @Test
    void testListarHistorial() {
        List<HistorialModel> historialList = List.of(historialModel);
        Page<HistorialModel> historialPage = new PageImpl<>(historialList);
        when(listHistorialUseCase.listarHistorial(any(Pageable.class))).thenReturn(historialPage);

        Page<HistorialModel> result = historialService.listarHistorial(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(historialModel.id(), result.getContent().get(0).id());
    }
}
