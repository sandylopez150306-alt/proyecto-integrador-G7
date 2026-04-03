package com.yachay.tech.repository;

import com.yachay.tech.model.Puntaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPuntajeRepository extends JpaRepository<Puntaje, Integer> {
}
