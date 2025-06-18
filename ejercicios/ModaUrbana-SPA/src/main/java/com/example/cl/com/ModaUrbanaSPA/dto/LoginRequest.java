package com.example.cl.com.ModaUrbanaSPA.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String nombreUsuario;
    private String contraseña;
}
