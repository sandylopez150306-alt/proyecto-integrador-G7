package com.yachay.tech.api.dto;
import com.yachay.tech.data.model.Usuario;

public record UsuarioRegistroDtoResponse(
        Integer id,
        String correo,
        String nombres,
        String rol
) {
    public UsuarioRegistroDtoResponse(Usuario usuario) {
        this(usuario.getIdUsuario(), usuario.getCorreo(), usuario.getNombres(), usuario.getRol().name());
    }
}