package com.yachay.tech.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "correo",nullable = false, unique = true, length = 150)
    private String correo;

    @Column(name = "contrasena",nullable = false)
    private String contrasena;

    @Column(name = "nombres",nullable = false, length = 100)
    private String nombres;

    @Column(name = "apepa",nullable = false, length = 100)
    private String apepa;

    @Column(name = "apema",nullable = false, length = 100)
    private String apema;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol",nullable = false)
    private Rol rol = Rol.ANALISTA;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public enum Rol {
        ADMINISTRADOR, ANALISTA
    }

    public Usuario() {
    }
}
