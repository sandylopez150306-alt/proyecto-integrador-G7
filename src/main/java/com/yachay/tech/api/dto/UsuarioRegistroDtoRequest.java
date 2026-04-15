package com.yachay.tech.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRegistroDtoRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombres,

        @NotBlank(message = "El apellido paterno es obligatorio")
        String apepa,

        @NotBlank(message = "El apellido materno es obligatorio")
        String apema,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Formato de correo inválido")
        @Pattern(
                regexp = "^[iI]\\d+@cibertec\\.edu\\.pe$",
                message = "Solo se permiten correos institucionales de Cibertec (ej. I202312345@cibertec.edu.pe)"
        )
        String correo,
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String contrasena


)
{}