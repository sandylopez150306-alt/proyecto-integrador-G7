package com.yachay.tech.data.repository;

import com.yachay.tech.data.model.Fase;
import com.yachay.tech.data.model.Puntaje;
import com.yachay.tech.data.model.SesionSimulador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPuntajeRepository extends JpaRepository<Puntaje, Integer> {
    boolean existsBySesionAndAlternativa_Fase(SesionSimulador sesion, Fase fase);
    boolean existsBySesionAndAlternativa_Grupo(SesionSimulador sesion, String grupo);
    java.util.Optional<Puntaje> findFirstBySesionOrderByFechaDecisionDesc(SesionSimulador sesion);
    java.util.List<Puntaje> findBySesion(SesionSimulador sesion);

    @org.springframework.data.jpa.repository.Query("SELECT COUNT(DISTINCT p.alternativa.grupo) FROM Puntaje p WHERE p.sesion = :sesion AND p.alternativa.fase.numeroFase = :numFase")
    long countDistinctGrupoBySesionAndFaseNumero(SesionSimulador sesion, Integer numFase);
}
