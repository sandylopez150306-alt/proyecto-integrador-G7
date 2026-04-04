package com.yachay.tech.data.repository;

import com.yachay.tech.data.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByCorreo(String correo);
    Optional<Usuario> findByCorreo(String correo);
}
