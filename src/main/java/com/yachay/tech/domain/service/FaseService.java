package com.yachay.tech.domain.service;

import com.yachay.tech.api.dto.FaseLecturaDtoResponse;
import com.yachay.tech.data.repository.IFaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaseService {

    @Autowired
    private IFaseRepository faseRepository;

    public FaseLecturaDtoResponse obtenerContenidoLectura(Integer numero) {
        return faseRepository.findByNumeroFase(numero)
                .map(FaseLecturaDtoResponse::new)
                .orElseThrow(() -> new EntityNotFoundException("Fase no encontrada"));
    }
}