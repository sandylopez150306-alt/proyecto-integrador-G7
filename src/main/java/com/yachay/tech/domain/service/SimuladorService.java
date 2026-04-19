package com.yachay.tech.domain.service;

import com.yachay.tech.api.dto.*;
import com.yachay.tech.api.exceptions.BadRequestException;
import com.yachay.tech.api.exceptions.ConflictException;
import com.yachay.tech.api.exceptions.ForbiddenException;
import com.yachay.tech.api.exceptions.NotFoundException;
import com.yachay.tech.data.model.*;
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

    @Autowired
    private FaseService faseService;

    @Transactional
    public SesionDtoResponse crearORecuperarSesion(Usuario usuario) {
        SesionSimulador sesion = sesionRepository.findByUsuarioAndCompletadoFalse(usuario)
                .orElseGet(() -> {
                    SesionSimulador nueva = new SesionSimulador();
                    nueva.setUsuario(usuario);
                    nueva.setFchInicio(LocalDateTime.now());
                    nueva.setCompletado(false);
                    nueva.setPuntajeTotal(0.0);
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
    public FaseDtoResponse registrarDecision(DecisionDtoRequest request, Usuario usuario) {
        SesionSimulador sesion = sesionRepository.findById(request.idSesion())
                .orElseThrow(() -> new NotFoundException("Sesión no encontrada."));
        if (!sesion.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new ForbiddenException("No tiene permiso para modificar esta sesión.");
        }
        if (sesion.getCompletado()) {
            throw new BadRequestException("La sesión ya fue completada.");
        }
        List<Alternativa> alternativas = alternativaRepository.findAllById(request.idsAlternativas());
        if (alternativas.size() != request.idsAlternativas().size() || alternativas.isEmpty()) {
            throw new NotFoundException("Una o más alternativas no existen.");
        }
        var faseList = alternativas.stream().map(a -> a.getFase().getIdFase()).distinct().toList();
        if (faseList.size() > 1) {
            throw new BadRequestException("Las alternativas seleccionadas pertenecen a distintas fases.");
        }
        Fase faseActual = alternativas.get(0).getFase();
        String grupo = alternativas.get(0).getGrupo();

        if (grupo != null && !"".equals(grupo.trim())) {
            if (puntajeRepository.existsBySesionAndAlternativa_Grupo(sesion, grupo)) {
                throw new ConflictException("Ya se ha registrado una decisión para el grupo " + grupo + " en esta sesión.");
            }
        } else {
            boolean yaDecidioEnFase = puntajeRepository.existsBySesionAndAlternativa_Fase(sesion, faseActual);
            if (yaDecidioEnFase) {
                throw new ConflictException("Ya se ha registrado una decisión para esta fase en esta sesión.");
            }
        }
        double puntajeObtenidoBatch = 0.0;
        var faseSiguiente = alternativas.get(0).getFaseSiguiente();
        Integer siguienteFaseId = (faseSiguiente != null) ? faseSiguiente.getIdFase() : faseActual.getIdFase();

        for (var alternativa : alternativas) {
            Puntaje puntaje = new Puntaje();
            puntaje.setSesion(sesion);
            puntaje.setAlternativa(alternativa);
            puntaje.setFechaDecision(LocalDateTime.now());
            puntajeRepository.save(puntaje);
            puntajeObtenidoBatch += alternativa.getPuntaje();
        }
        sesion.setPuntajeTotal(sesion.getPuntajeTotal() + puntajeObtenidoBatch);
        sesionRepository.save(sesion);
        return obtenerFaseActual(usuario);
    }

    public FaseDtoResponse obtenerFaseActual(Usuario usuario) {
        SesionSimulador sesion = sesionRepository.findByUsuarioAndCompletadoFalse(usuario)
                .orElseThrow(() -> new NotFoundException("No hay sesión activa."));

        long gruposFase1 = puntajeRepository.countDistinctGrupoBySesionAndFaseNumero(sesion, 1);
        if (gruposFase1 < 3) {
            return new FaseDtoResponse(faseRepository.findByNumeroFase(1).get());
        }

        long gruposFase2 = puntajeRepository.countDistinctGrupoBySesionAndFaseNumero(sesion, 2);
        if (gruposFase2 < 2) {
            return new FaseDtoResponse(faseRepository.findByNumeroFase(2).get());
        }

        long gruposFase3 = puntajeRepository.countDistinctGrupoBySesionAndFaseNumero(sesion, 3);
        if (gruposFase3 < 3) {
            return new FaseDtoResponse(faseRepository.findByNumeroFase(3).get());
        }

        sesion.setCompletado(true);
        sesionRepository.save(sesion);
        return null;
    }

    public HistorialSesionDtoResponse obtenerHistorial(Usuario usuario) {
        var sesiones = sesionRepository.findAllByUsuarioOrderByFchInicioDesc(usuario);
        if (sesiones.isEmpty()) throw new NotFoundException("No hay historial.");
        
        SesionSimulador ultima = sesiones.get(0);
        var puntajes = puntajeRepository.findBySesion(ultima);
        
        List<DetalleDecisionDtoResponse> detalles = puntajes.stream()
                .map(p -> new DetalleDecisionDtoResponse(
                        p.getAlternativa().getFase().getNomFase(),
                        p.getAlternativa().getDescAlternativa(),
                        p.getAlternativa().getRetroalimentacion(),
                        p.getAlternativa().getPuntaje()
                )).toList();

        return new HistorialSesionDtoResponse(
                ultima.getIdSesion(),
                ultima.getFchInicio(),
                ultima.getCompletado(),
                ultima.getPuntajeTotal(),
                detalles
        );
    }
}