package com.example.cl.com.ModaUrbanaSPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cl.com.ModaUrbanaSPA.dto.LoginRequest;
import com.example.cl.com.ModaUrbanaSPA.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(
                loginRequest.getNombreUsuario(),
                loginRequest.getContraseña());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard() {
        return ResponseEntity.ok("Acceso concedido a ADMIN");
    }
}
