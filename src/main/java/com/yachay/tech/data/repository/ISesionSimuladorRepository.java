package com.yachay.tech.data.repository;

import com.yachay.tech.data.model.SesionSimulador;
import com.yachay.tech.data.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISesionSimuladorRepository extends JpaRepository<SesionSimulador, Integer> {
    Optional<SesionSimulador> findByUsuarioAndCompletadoFalse(Usuario usuario);
    java.util.List<SesionSimulador> findAllByUsuarioOrderByFchInicioDesc(Usuario usuario);
}
