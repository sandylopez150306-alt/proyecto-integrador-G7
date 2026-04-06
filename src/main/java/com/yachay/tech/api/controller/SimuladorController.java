package com.yachay.tech.api.controller;

import com.yachay.tech.api.dto.DecisionDtoRequest;
import com.yachay.tech.api.dto.DecisionDtoResponse;
import com.yachay.tech.api.dto.SesionDtoResponse;
import com.yachay.tech.data.model.Usuario;
import com.yachay.tech.domain.service.SimuladorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulador")
public class SimuladorController {

    @Autowired
    private SimuladorService simuladorService;

    @PostMapping("/sesiones")
    public ResponseEntity<SesionDtoResponse> crearSesion(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        var sesion = simuladorService.crearORecuperarSesion(usuario);
        return ResponseEntity.ok(sesion);
    }

    @GetMapping("/sesiones/activa")
    public ResponseEntity<SesionDtoResponse> obtenerSesionActiva(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        var sesion = simuladorService.obtenerSesionActiva(usuario);
        return ResponseEntity.ok(sesion);
    }

    @PostMapping("/sesiones/{sesionId}/decisiones")
    public ResponseEntity<DecisionDtoResponse> registrarDecision(
            @PathVariable Integer sesionId,
            @RequestBody @Valid DecisionDtoRequest request,
            Authentication authentication
    ) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        var resultado = simuladorService.registrarDecision(sesionId, request.idsAlternativas(), usuario);
        return ResponseEntity.ok(resultado);
    }
}
