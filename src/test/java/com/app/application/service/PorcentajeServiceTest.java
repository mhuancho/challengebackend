package com.app.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import com.app.domain.constants.Constants;
import com.app.domain.exception.PorcentajeServiceUnavailableException;
import com.app.domain.port.in.ObtenerPorcentajeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

@ExtendWith(MockitoExtension.class)
public class PorcentajeServiceTest {

    @Mock
    private ObtenerPorcentajeUseCase obtenerPorcentajeUseCase;

    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    private PorcentajeService porcentajeService;

    private Cache cache;

    @BeforeEach
    public void setUp() {
        cache = new ConcurrentMapCache(Constants.PERCENTAGE_FIELD);
        when(cacheManager.getCache(Constants.PERCENTAGE_FIELD)).thenReturn(cache);
    }

    @Test
    public void testRecuperarConCache() {
        double expectedPorcentaje = 10.0;
        cache.put(Constants.PERCENTAGE_FIELD, expectedPorcentaje);

        double porcentaje = porcentajeService.recuperar(new Exception());

        assertEquals(expectedPorcentaje, porcentaje);
    }

    @Test
    public void testRecuperarSinCache() {
        assertThrows(PorcentajeServiceUnavailableException.class, () -> {
            porcentajeService.recuperar(new Exception());
        });
    }

}
