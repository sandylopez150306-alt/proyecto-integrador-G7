package com.yachay.tech.api.dto;

public record ResultadoDto(
        String nombreEstudiante,
        Integer puntajeTotal,
        String nivelAlcanzado,
        String fechaCompletado
) {
}
