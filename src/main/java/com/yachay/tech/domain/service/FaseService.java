package com.yachay.tech.domain.service;

import com.yachay.tech.api.dto.FaseDtoResponse;
import com.yachay.tech.data.model.Fase;
import com.yachay.tech.data.repository.IFaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaseService {

    @Autowired
    private IFaseRepository faseRepository;

    public FaseDtoResponse obtenerFasePorId(Integer id) {
        Fase fase = faseRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Fase no encontrada"));

        return new FaseDtoResponse(fase);
    }
}