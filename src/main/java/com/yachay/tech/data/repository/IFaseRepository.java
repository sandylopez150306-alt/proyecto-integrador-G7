package com.yachay.tech.data.repository;

import com.yachay.tech.data.model.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFaseRepository extends JpaRepository<Fase, Integer> {
}
