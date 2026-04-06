package com.yachay.tech.api.controller;

import com.yachay.tech.api.dto.LoginDtoRequest;
import com.yachay.tech.api.dto.LoginDtoResponse;
import com.yachay.tech.api.dto.UsuarioRegistroDtoRequest;
import com.yachay.tech.api.dto.UsuarioRegistroDtoResponse;
import com.yachay.tech.api.dto.ForgotPasswordDtoRequest;
import com.yachay.tech.api.dto.ResetPasswordDtoRequest;
import com.yachay.tech.api.exceptions.UnauthorizedException;
import com.yachay.tech.security.auth.UsuarioPrincipal;
import com.yachay.tech.data.model.Usuario;
import com.yachay.tech.domain.service.UsuarioService;
import com.yachay.tech.security.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AutenticacionController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioRegistroDtoResponse> registrar(@RequestBody @Valid UsuarioRegistroDtoRequest datos) {
        Usuario usuarioGuardado = usuarioService.registrar(datos);
        UsuarioRegistroDtoResponse respuesta = new UsuarioRegistroDtoResponse(usuarioGuardado);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDtoRequest datos) {
        try {
           Authentication authToken = new UsernamePasswordAuthenticationToken(datos.correo(), datos.contrasena());
           var usuarioAutenticado = authenticationManager.authenticate(authToken);

            UsuarioPrincipal principal = (UsuarioPrincipal) usuarioAutenticado.getPrincipal();
            Usuario usuario = principal.getUsuario();
            String jwtToken = tokenService.generarToken(usuario);
            return ResponseEntity.ok(new LoginDtoResponse(
                    jwtToken,
                    "Bearer",
                    usuario.getNombres(),
                    usuario.getRol().name()
            ));

        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Correo o contraseña incorrectos");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid ForgotPasswordDtoRequest request) {
        usuarioService.solicitarRecuperacionContrasena(request.correo());
        return ResponseEntity.ok("Si el correo se encuentra registrado, recibirá instrucciones para restablecer su contraseña.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordDtoRequest request) {
        try {
            usuarioService.resetearContrasena(request.token(), request.nuevaContrasena());
            return ResponseEntity.ok("Contraseña restablecida exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}