package com.yachay.tech.api.dto;

import com.yachay.tech.data.model.Alternativa;

public record AlternativaDtoResponse(
        Integer idAlternativa,
        String descAlternativa,
        Integer puntaje,
        String retroalimentacion,
        Integer orden
) {
    public AlternativaDtoResponse(Alternativa alternativa) {
        this(
                alternativa.getIdAlternativa(),
                alternativa.getDescAlternativa(),
                alternativa.getPuntaje(),
                alternativa.getRetroalimentacion(),
                alternativa.getOrden()
        );
    }
}
