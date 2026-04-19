package com.yachay.tech.api.dto;

import com.yachay.tech.data.model.Alternativa;

public record AlternativaDtoResponse(
        Integer idAlternativa,
        String descAlternativa,
        Double puntaje,
        String retroalimentacion,
        Integer orden,
        Integer faseSiguienteId,
        String grupo
) {
    public AlternativaDtoResponse(Alternativa alternativa) {
        this(
                alternativa.getIdAlternativa(),
                alternativa.getDescAlternativa(),
                alternativa.getPuntaje(),
                alternativa.getRetroalimentacion(),
                alternativa.getOrden(),
                alternativa.getFaseSiguiente() != null ? alternativa.getFaseSiguiente().getIdFase() : null,
                alternativa.getGrupo()
        );
    }
}
