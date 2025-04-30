package com.app.infrastructure.restapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external-api/porcentaje")
public class ExternalPorcentajeController {

    @GetMapping
    public ResponseEntity<Double> getPorcentaje() {
        return ResponseEntity.ok(10.0);
    }
}
