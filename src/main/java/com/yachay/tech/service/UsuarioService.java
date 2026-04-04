package com.yachay.tech.service;

import com.yachay.tech.dto.UsuarioRegistroDtoRequest;
import com.yachay.tech.exceptions.ConflictException;
import com.yachay.tech.model.Usuario;
import com.yachay.tech.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

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

        return usuarioRepository.save(usuario);
    }
}

