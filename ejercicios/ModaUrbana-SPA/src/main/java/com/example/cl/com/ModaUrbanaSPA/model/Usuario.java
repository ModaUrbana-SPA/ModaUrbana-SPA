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
    @Column(length = 50, nullable = false)
    private String nombreUsuario;
    @Column(length = 50, nullable = false)
    private String correo;
    @Column(length = 100, nullable = false)
    private String contraseña;
    @Column(length = 50, nullable = false)
    private String rol;

}
