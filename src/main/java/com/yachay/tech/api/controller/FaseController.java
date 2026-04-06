package com.yachay.tech.api.controller;

import com.yachay.tech.api.dto.AlternativaDtoResponse;
import com.yachay.tech.api.dto.FaseLecturaDtoResponse;
import com.yachay.tech.domain.service.FaseService;
import com.yachay.tech.domain.service.SimuladorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fases")
public class FaseController {

    @Autowired
    private FaseService faseService;

    @Autowired
    private SimuladorService simuladorService;

    @GetMapping("/{numero}")
    public ResponseEntity<FaseLecturaDtoResponse> obtenerFase(@PathVariable Integer numero) {
        var response = faseService.obtenerContenidoLectura(numero);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{numero}/alternativas")
    public ResponseEntity<List<AlternativaDtoResponse>> obtenerAlternativas(@PathVariable Integer numero) {
        var alternativas = simuladorService.obtenerAlternativasPorFase(numero);
        return ResponseEntity.ok(alternativas);
    }
}