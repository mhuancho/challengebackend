package com.app.infrastructure.restapi.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import com.app.application.service.HistorialService;
import com.app.domain.dto.HistorialDto;
import com.app.domain.model.HistorialModel;
import com.app.infrastructure.restapi.mapper.HistoryMapper;
import com.app.infrastructure.restapi.metadata.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class HistorialControllerTest {

    @Mock
    private HistorialService historialService;

    @Mock
    private HistoryMapper historyMapper;

    @InjectMocks
    private HistorialController historialController;

    @Test
    void testListarHistorial() throws Exception {
        // Arrange
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        HistorialModel model1 = new HistorialModel(
                1L,
                LocalDateTime.now(),
                "/api/test",
                "param=test",
                "OK",
                null
        );

        HistorialDto dto1 = new HistorialDto(
                1L,
                "param=test",
                "OK",null,null,null
        );

        List<HistorialModel> modelList = List.of(model1);
        List<HistorialDto> dtoList = List.of(dto1);
        Page<HistorialModel> pageResult = new PageImpl<>(modelList, pageable, 1);

        when(historialService.listarHistorial(pageable)).thenReturn(pageResult);
        when(historyMapper.toDtoList(modelList)).thenReturn(dtoList);

        CompletableFuture<ResponseEntity<ApiResponse>> futureResponse =
                historialController.listarHistorial(page, size);
        ResponseEntity<ApiResponse> response = futureResponse.get();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("OK", response.getBody().getMeta().getResult());
        assertEquals(1, response.getBody().getMeta().getCantidadRegistros());
        assertEquals(dtoList, response.getBody().getData());
    }

}
