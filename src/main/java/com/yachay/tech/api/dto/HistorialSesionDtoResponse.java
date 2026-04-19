package com.yachay.tech.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record HistorialSesionDtoResponse(
        Integer idSesion,
        LocalDateTime fchInicio,
        Boolean completado,
        Double puntajeTotal,
        List<DetalleDecisionDtoResponse> decisiones
) {}
