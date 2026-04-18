package com.yachay.tech.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DecisionDtoRequest(
        @NotEmpty(message = "Debe enviar al menos una alternativa seleccionada")
        List<Integer> idsAlternativas,

        @NotNull(message = "El ID de sesión es obligatorio")
        Integer idSesion
) {
}
