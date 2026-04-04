package com.yachay.tech.api.dto;

import com.yachay.tech.data.model.Fase;

public record FaseLecturaDtoResponse(
        Integer id,
        Integer numero,
        String nombre,
        String contenido
) {
    public FaseLecturaDtoResponse(Fase fase) {
        this(fase.getIdFase(), fase.getNumeroFase(), fase.getNomFase(), fase.getDescFase());
    }
}