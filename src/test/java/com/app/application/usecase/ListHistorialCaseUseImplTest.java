package com.app.application.usecase;

import com.app.domain.model.HistorialModel;
import com.app.domain.port.out.HistorialRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.Collections;

class ListHistorialCaseUseImplTest {

    @Mock
    private HistorialRepositoryPort historialRepositoryPort;

    @InjectMocks
    private ListHistorialCaseUseImpl listHistorialCaseUse;
    private HistorialModel historialModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
    void testListarHistorial() {
        List<HistorialModel> historialList = List.of(historialModel);
        Page<HistorialModel> historialPage = new PageImpl<>(historialList, PageRequest.of(0, 10), historialList.size());
        when(historialRepositoryPort.listarHistorial(any(Pageable.class))).thenReturn(historialPage);
        Page<HistorialModel> result = listHistorialCaseUse.listarHistorial(PageRequest.of(0, 10));
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals(historialModel.id(), result.getContent().get(0).id());
    }

    @Test
    void testListarHistorialConVacio() {
        Page<HistorialModel> historialPageVacia = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);
        when(historialRepositoryPort.listarHistorial(any(Pageable.class))).thenReturn(historialPageVacia);
        Page<HistorialModel> result = listHistorialCaseUse.listarHistorial(PageRequest.of(0, 10));
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
    }
}

