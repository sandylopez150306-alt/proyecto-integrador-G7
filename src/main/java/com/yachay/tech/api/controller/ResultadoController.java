package com.yachay.tech.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @GetMapping
    public ResponseEntity<?> verRendimiento() {
        return ResponseEntity.ok(List.of(
                Map.of("estudiante", "Sandy Lopez", "puntaje", 100),
                Map.of("estudiante", "Usuario Docente", "puntaje", 90)
        ));
    }
}