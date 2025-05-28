package com.example.cl.com.ModaUrbanaSPA.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String correo;
    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}
