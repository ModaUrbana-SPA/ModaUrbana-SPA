package com.example.cl.com.ModaUrbanaSPA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cl.com.ModaUrbanaSPA.config.JwtUtil;
import com.example.cl.com.ModaUrbanaSPA.model.Usuario;
import com.example.cl.com.ModaUrbanaSPA.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String nombreUsuario, String contraseña) {
        // buscando un usuario por su nombre de usuario
        Usuario usuario = usuarioRepositorio.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // verificando si la contraseña proporcionada coincide con la almacenada
        if (!usuario.getContraseña().equals(contraseña)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // generando un token JWT para el usuario autenticado
        return jwtUtil.generateToken(usuario.getNombreUsuario(), usuario.getRol());
    }
}
