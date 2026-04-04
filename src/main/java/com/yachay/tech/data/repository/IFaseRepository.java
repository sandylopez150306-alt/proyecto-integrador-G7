package com.yachay.tech.data.repository;

import com.yachay.tech.data.model.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFaseRepository extends JpaRepository<Fase, Integer> {
    Optional<Fase> findByNumeroFase(Integer numeroFase);
}
