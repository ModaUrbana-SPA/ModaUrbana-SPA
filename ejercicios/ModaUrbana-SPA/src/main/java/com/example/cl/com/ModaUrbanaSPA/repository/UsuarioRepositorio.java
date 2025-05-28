package com.example.cl.com.ModaUrbanaSPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cl.com.ModaUrbanaSPA.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
}
