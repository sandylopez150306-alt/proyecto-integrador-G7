package com.yachay.tech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fases")
public class Fase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fase")
    private Integer idFase;

    @Column(name = "numero_fase", nullable = false, unique = true)
    private Integer numeroFase;

    @Column(name = "nom_fase", nullable = false, length = 100)
    private String nomFase;

    @Column(name = "desc_Fase", columnDefinition = "TEXT")
    private String descFase;

    public Fase() {
    }
}