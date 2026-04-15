package com.yachay.tech.api.controller;

import com.yachay.tech.api.dto.AlternativaDtoResponse;
import com.yachay.tech.api.dto.FaseDtoResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<FaseDtoResponse> obtenerFase(@PathVariable Integer id) {
        var response = faseService.obtenerFasePorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/alternativas")
    public ResponseEntity<List<AlternativaDtoResponse>> obtenerAlternativas(@PathVariable Integer id) {
        var alternativas = simuladorService.obtenerAlternativasPorFase(id);
        return ResponseEntity.ok(alternativas);
    }
}