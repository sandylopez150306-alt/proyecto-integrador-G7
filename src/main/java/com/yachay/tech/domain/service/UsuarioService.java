package com.yachay.tech.domain.service;

import com.yachay.tech.api.dto.UsuarioRegistroDtoRequest;
import com.yachay.tech.api.exceptions.ConflictException;
import com.yachay.tech.data.model.Usuario;
import com.yachay.tech.data.repository.IUsuarioRepository;
import com.yachay.tech.security.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @Transactional
    public Usuario registrar(UsuarioRegistroDtoRequest datos) {

        if (usuarioRepository.existsByCorreo(datos.correo())) {
            throw new ConflictException("El correo electrónico ya se encuentra registrado.");

        }
        Usuario usuario = new Usuario();
        usuario.setCorreo(datos.correo());
        usuario.setNombres(datos.nombres());
        usuario.setApepa(datos.apepa());
        usuario.setApema(datos.apema());

        String passwordCifrada = passwordEncoder.encode(datos.contrasena());
        usuario.setContrasena(passwordCifrada);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        emailService.enviarCorreo(
                datos.correo(),
                "Bienvenido a Yachay Pro",
                "bienvenido",
                Map.of("nombre", datos.nombres())
        );

        return usuarioGuardado;
    }

    public void solicitarRecuperacionContrasena(String correo) {
        usuarioRepository.findByCorreo(correo).ifPresent(usuario -> {
            String token = tokenService.generarTokenRecuperacion(usuario);
            String resetLink = "http://localhost:4200/reset-password?token=" + token;

            emailService.enviarCorreo(
                    usuario.getCorreo(),
                    "Recuperación de Contraseña - Yachay-Pro",
                    "recuperacion-contrasena",
                    Map.of(
                            "nombre", usuario.getNombres(),
                            "enlace", resetLink
                    )
            );
        });
    }

    @Transactional
    public void resetearContrasena(String token, String nuevaContrasena) {
        String correo = tokenService.validarTokenRecuperacion(token);
        
        if (correo == null) {
            throw new IllegalArgumentException("Token inválido o expirado");
        }

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String passwordCifrada = passwordEncoder.encode(nuevaContrasena);
        usuario.setContrasena(passwordCifrada);
        
        usuarioRepository.save(usuario);
    }
}

