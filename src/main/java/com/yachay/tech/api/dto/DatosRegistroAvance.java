package com.yachay.tech.api.dto;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroAvance(
        @NotNull
        Integer faseAEnviar
) {
}
