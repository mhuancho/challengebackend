package com.app.domain.config;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "porcentaje-client", url = "${external.porcentaje-service.url}")
public interface PorcentajeFeignClient {

    @GetMapping("/external-api/porcentaje")
    Double getPorcentaje();
}
