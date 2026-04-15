package com.yachay.tech.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "fase", cascade = CascadeType.ALL)
    private List<Alternativa> alternativas = new ArrayList<>();

    public Fase() {
    }
}