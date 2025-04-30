package com.app.application.service;

import com.app.domain.exception.PorcentajeServiceUnavailableException;
import com.app.domain.port.in.ObtenerPorcentajeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import static com.app.domain.constants.Constants.ERROR_500;
import static com.app.domain.constants.Constants.PERCENTAGE_FIELD;

@Primary
@Service
@AllArgsConstructor
public class PorcentajeService {

    private final ObtenerPorcentajeUseCase obtenerPorcentajeUseCase;
    private final CacheManager cacheManager;

    @Retryable(
            value = { Exception.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    @Cacheable(value = PERCENTAGE_FIELD, unless = "#result == null")
    public double obtenerPorcentaje() {
        return obtenerPorcentajeUseCase.obtenerPorcentaje();
    }
    @Recover
    public double recuperar(Exception e) {
        Cache cache = cacheManager.getCache(PERCENTAGE_FIELD);
        if (cache != null) {
            Double porcentajeCacheado = cache.get(PERCENTAGE_FIELD, Double.class);
            if (porcentajeCacheado != null) {
                return porcentajeCacheado;
            }
        }
        throw new PorcentajeServiceUnavailableException(ERROR_500);
    }

}