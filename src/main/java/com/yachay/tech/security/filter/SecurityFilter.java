package com.yachay.tech.security.filter;

import com.yachay.tech.data.repository.IUsuarioRepository;
import com.yachay.tech.security.auth.TokenService;
import com.yachay.tech.security.auth.UsuarioPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.replace("Bearer ", "");

            var nombreUsuario = tokenService.getSubject(token);

            if (nombreUsuario != null) {
                var usuario = usuarioRepository.findByCorreo(nombreUsuario)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, new UsuarioPrincipal(usuario).getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}