package com.yachay.tech.api.controller;

import com.yachay.tech.api.dto.*;
import com.yachay.tech.data.model.Usuario;
import com.yachay.tech.domain.service.ChatSimuladorService;
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

    @Autowired
    private ChatSimuladorService chatSimuladorService;

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
    public ResponseEntity<FaseDtoResponse> registrarDecision(
            @PathVariable Integer sesionId,
            @RequestBody @Valid DecisionDtoRequest request,
            Authentication authentication
    ) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        FaseDtoResponse siguienteFase = simuladorService.registrarDecision(request, usuario);

        return ResponseEntity.ok(siguienteFase);
    }

    @GetMapping("/fase-actual")
    public ResponseEntity<FaseDtoResponse> obtenerFaseActual(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        var fase = simuladorService.obtenerFaseActual(usuario);
        return ResponseEntity.ok(fase);
    }

    @GetMapping("/historial")
    public ResponseEntity<HistorialSesionDtoResponse> obtenerHistorial(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        var historial = simuladorService.obtenerHistorial(usuario);
        return ResponseEntity.ok(historial);
    }

    /**
     * AI coaching chatbot for the final report.
     * The frontend sends the user's message and the report context (stringified).
     */
    @PostMapping("/chat")
    public ResponseEntity<ChatSimuladorResponse> chat(
            @RequestBody ChatSimuladorRequest request,
            Authentication authentication
    ) {
        var respuesta = chatSimuladorService.chat(request);
        return ResponseEntity.ok(respuesta);
    }
}
