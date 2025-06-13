package com.example.cl.com.ModaUrbanaSPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cl.com.ModaUrbanaSPA.service.AuthService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String nombreUsuario, @RequestParam String contraseña) {
        String token = authService.login(nombreUsuario, contraseña);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Acceso concedido a ADMIN");
    }
}
