package com.yachay.tech.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sesiones_simulador")
public class SesionSimulador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Integer idSesion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "fch_inicio")
    private LocalDateTime fchInicio;

    @Column(name = "completado")
    private Boolean completado = false;

    @Column(name = "puntaje_total")
    private Double puntajeTotal = 0.0;

    public SesionSimulador() {
    }
}