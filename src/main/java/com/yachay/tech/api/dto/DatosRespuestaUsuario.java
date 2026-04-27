package com.yachay.tech.api.dto;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String correo,
        String rol,
        Integer ultimaFase
) {
}
