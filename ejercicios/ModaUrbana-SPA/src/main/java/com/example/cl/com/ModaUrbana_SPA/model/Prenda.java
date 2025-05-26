package com.example.cl.com.ModaUrbana_SPA.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
// lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
// DATA
@Table(name = "PRENDA")
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    private String talla;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Integer stock;

}
