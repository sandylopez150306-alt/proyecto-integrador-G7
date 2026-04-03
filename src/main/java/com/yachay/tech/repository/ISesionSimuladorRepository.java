package com.yachay.tech.repository;

import com.yachay.tech.model.SesionSimulador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISesionSimuladorRepository extends JpaRepository<SesionSimulador, Integer> {
}
