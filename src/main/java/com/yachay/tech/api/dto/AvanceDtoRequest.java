package com.yachay.tech.api.dto;

import jakarta.validation.constraints.NotNull;

public record AvanceDtoRequest(
        @NotNull(message = "El número de fase es obligatorio")
        Integer faseAEnviar
) {
}