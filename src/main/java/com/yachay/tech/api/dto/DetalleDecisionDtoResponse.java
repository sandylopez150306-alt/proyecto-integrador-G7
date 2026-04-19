package com.yachay.tech.api.dto;

public record DetalleDecisionDtoResponse(
        String nombreFase,
        String descripcionAlternativa,
        String retroalimentacion,
        Double puntajeObtenido
) {}
