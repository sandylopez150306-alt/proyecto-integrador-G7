package com.yachay.tech.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alternativas")
public class Alternativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlternativa;

    @ManyToOne
    @JoinColumn(name = "fase_id", nullable = false)
    private Fase fase;

    @Column(name = "desc_alternativa", nullable = false, columnDefinition = "TEXT")
    private String descAlternativa;

    @Column(name = "puntaje", nullable = false)
    private Integer puntaje;

    @Column(name = "retroalimentacion", columnDefinition = "TEXT")
    private String retroalimentacion;

    @Column(name = "orden")
    private Integer orden;

    public Alternativa() {
    }
}