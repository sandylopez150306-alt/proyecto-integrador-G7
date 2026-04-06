package com.yachay.tech.domain.service;

import com.yachay.tech.api.dto.UsuarioRegistroDtoRequest;
import com.yachay.tech.api.exceptions.ConflictException;
import com.yachay.tech.data.model.Usuario;
import com.yachay.tech.data.repository.IUsuarioRepository;
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
}

