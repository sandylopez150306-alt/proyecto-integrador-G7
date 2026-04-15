package com.yachay.tech.api.dto;

import com.yachay.tech.data.model.Fase;

import java.util.List;

public record FaseDtoResponse(
        Integer id,
        Integer numero,
        String nombre,
        String contenido,
        List<AlternativaDtoResponse> alternativas
) {
    public FaseDtoResponse(Fase fase) {
        this(
                fase.getIdFase(),
                fase.getNumeroFase(),
                fase.getNomFase(),
                fase.getDescFase(),
                fase.getAlternativas() != null ?
                        fase.getAlternativas().stream()
                                .map(AlternativaDtoResponse::new)
                                .toList() : List.of()
        );
    }
}