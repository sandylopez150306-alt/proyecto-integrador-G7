package com.yachay.tech.api.controller;

import com.yachay.tech.api.dto.FaseLecturaDtoResponse;
import com.yachay.tech.domain.service.FaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fases")
public class FaseController {

    @Autowired
    private FaseService faseService;

    @GetMapping("/{numero}")
    public ResponseEntity<FaseLecturaDtoResponse> obtenerFase(@PathVariable Integer numero) {
        var response = faseService.obtenerContenidoLectura(numero);
        return ResponseEntity.ok(response);
    }
}