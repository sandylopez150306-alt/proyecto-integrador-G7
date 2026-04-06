package com.yachay.tech.api.dto;

import com.yachay.tech.data.model.SesionSimulador;

import java.time.LocalDateTime;

public record SesionDtoResponse(
        Integer idSesion,
        LocalDateTime fchInicio,
        Boolean completado
) {
    public SesionDtoResponse(SesionSimulador sesion) {
        this(
                sesion.getIdSesion(),
                sesion.getFchInicio(),
                sesion.getCompletado()
        );
    }
}
