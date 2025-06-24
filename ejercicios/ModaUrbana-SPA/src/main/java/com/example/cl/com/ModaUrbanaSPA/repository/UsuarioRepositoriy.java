package com.example.cl.com.ModaUrbanaSPA.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cl.com.ModaUrbanaSPA.model.Usuario;

public interface UsuarioRepositoriy extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
