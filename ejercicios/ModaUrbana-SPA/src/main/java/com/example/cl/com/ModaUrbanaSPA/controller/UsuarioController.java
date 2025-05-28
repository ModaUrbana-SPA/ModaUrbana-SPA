package com.example.cl.com.ModaUrbanaSPA.controller;

import com.example.cl.com.ModaUrbanaSPA.model.Usuario;
import com.example.cl.com.ModaUrbanaSPA.repository.UsuarioRepositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

}
