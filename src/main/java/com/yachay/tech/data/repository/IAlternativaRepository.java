package com.yachay.tech.data.repository;

import com.yachay.tech.data.model.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlternativaRepository extends JpaRepository<Alternativa, Integer> {
    List<Alternativa> findByFaseIdFaseOrderByOrdenAsc(Integer faseId);
}
