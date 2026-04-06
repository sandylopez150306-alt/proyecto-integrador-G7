package com.yachay.tech.domain.service;

import com.yachay.tech.api.dto.AlternativaDtoResponse;
import com.yachay.tech.api.dto.DecisionDtoResponse;
import com.yachay.tech.api.dto.SesionDtoResponse;
import com.yachay.tech.api.exceptions.BadRequestException;
import com.yachay.tech.api.exceptions.ConflictException;
import com.yachay.tech.api.exceptions.ForbiddenException;
import com.yachay.tech.api.exceptions.NotFoundException;
import com.yachay.tech.data.model.Alternativa;
import com.yachay.tech.data.model.Puntaje;
import com.yachay.tech.data.model.SesionSimulador;
import com.yachay.tech.data.model.Usuario;
import com.yachay.tech.data.repository.IAlternativaRepository;
import com.yachay.tech.data.repository.IFaseRepository;
import com.yachay.tech.data.repository.IPuntajeRepository;
import com.yachay.tech.data.repository.ISesionSimuladorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SimuladorService {

    @Autowired
    private ISesionSimuladorRepository sesionRepository;

    @Autowired
    private IAlternativaRepository alternativaRepository;

    @Autowired
    private IPuntajeRepository puntajeRepository;

    @Autowired
    private IFaseRepository faseRepository;

    @Transactional
    public SesionDtoResponse crearORecuperarSesion(Usuario usuario) {
        SesionSimulador sesion = sesionRepository.findByUsuarioAndCompletadoFalse(usuario)
                .orElseGet(() -> {
                    SesionSimulador nueva = new SesionSimulador();
                    nueva.setUsuario(usuario);
                    nueva.setFchInicio(LocalDateTime.now());
                    nueva.setCompletado(false);
                    nueva.setPuntajeTotal(0);
                    return sesionRepository.save(nueva);
                });

        return new SesionDtoResponse(sesion);
    }

    public SesionDtoResponse obtenerSesionActiva(Usuario usuario) {
        SesionSimulador sesion = sesionRepository.findByUsuarioAndCompletadoFalse(usuario)
                .orElseThrow(() -> new NotFoundException("No existe una sesión activa para este usuario."));

        return new SesionDtoResponse(sesion);
    }

    public List<AlternativaDtoResponse> obtenerAlternativasPorFase(Integer numeroFase) {
        var fase = faseRepository.findByNumeroFase(numeroFase)
                .orElseThrow(() -> new NotFoundException("Fase no encontrada con número: " + numeroFase));

        return alternativaRepository.findByFaseIdFaseOrderByOrdenAsc(fase.getIdFase())
                .stream()
                .map(AlternativaDtoResponse::new)
                .toList();
    }

    @Transactional
    public DecisionDtoResponse registrarDecision(Integer sesionId, List<Integer> alternativaIds, Usuario usuario) {
        SesionSimulador sesion = sesionRepository.findById(sesionId)
                .orElseThrow(() -> new NotFoundException("Sesión no encontrada."));

        if (!sesion.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new ForbiddenException("No tiene permiso para modificar esta sesión.");
        }

        if (sesion.getCompletado()) {
            throw new BadRequestException("La sesión ya fue completada.");
        }

        List<Alternativa> alternativas = alternativaRepository.findAllById(alternativaIds);
        if (alternativas.size() != alternativaIds.size() || alternativas.isEmpty()) {
            throw new NotFoundException("Una o más alternativas no existen.");
        }

        // Validar que todas las alternativas pertenezcan a la misma fase
        var faseList = alternativas.stream().map(a -> a.getFase().getIdFase()).distinct().toList();
        if (faseList.size() > 1) {
            throw new BadRequestException("Las alternativas seleccionadas pertenecen a distintas fases.");
        }

        // Validar que no hayamos tomado la decisión de esta fase previamente
        boolean yaDecidioEnFase = puntajeRepository.existsBySesionAndAlternativa_Fase(sesion, alternativas.get(0).getFase());
        if (yaDecidioEnFase) {
            throw new ConflictException("Ya se ha registrado una decisión para esta fase en la sesión actual.");
        }

        int puntajeObtenidoBatch = 0;
        StringBuilder feedbackBatch = new StringBuilder();

        for (var alternativa : alternativas) {
            Puntaje puntaje = new Puntaje();
            puntaje.setSesion(sesion);
            puntaje.setAlternativa(alternativa);
            puntaje.setFechaDecision(LocalDateTime.now());
            puntajeRepository.save(puntaje);

            puntajeObtenidoBatch += alternativa.getPuntaje();
            feedbackBatch.append("• ").append(alternativa.getRetroalimentacion()).append("\n\n");
        }

        sesion.setPuntajeTotal(sesion.getPuntajeTotal() + puntajeObtenidoBatch);
        sesionRepository.save(sesion);

        return new DecisionDtoResponse(
                -1, // No hay un solo idPuntaje ahora, es un lote
                feedbackBatch.toString().trim()
        );
    }
}
