package com.yachay.tech.data.repository;

import com.yachay.tech.data.model.Puntaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPuntajeRepository extends JpaRepository<Puntaje, Integer> {
}
