package com.yachay.tech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "puntajes")
public class Puntaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntaje;

    @ManyToOne
    @JoinColumn(name = "sesion_id", nullable = false)
    private SesionSimulador sesion;

    @ManyToOne
    @JoinColumn(name = "alternativa_id", nullable = false)
    private Alternativa alternativa;

    @Column(name = "fecha_decision")
    private LocalDateTime fechaDecision;
}