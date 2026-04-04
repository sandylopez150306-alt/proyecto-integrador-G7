package com.yachay.tech.repository;

import com.yachay.tech.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByCorreo(String correo);
    Optional<Usuario> findByCorreo(String correo);
}
