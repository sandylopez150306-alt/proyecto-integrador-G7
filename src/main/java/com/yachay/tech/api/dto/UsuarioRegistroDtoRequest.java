package com.yachay.tech.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRegistroDtoRequest(
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Formato de correo inválido")
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String contrasena,

        @NotBlank(message = "El nombre es obligatorio")
        String nombres,

        @NotBlank(message = "El apellido paterno es obligatorio")
        String apepa,

        @NotBlank(message = "El apellido materno es obligatorio")
        String apema
) {}