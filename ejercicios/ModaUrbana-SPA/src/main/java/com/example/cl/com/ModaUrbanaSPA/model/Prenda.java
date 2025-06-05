package com.example.cl.com.ModaUrbanaSPA.model;

import jakarta.persistence.*;
import lombok.*;



// lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
// DATA
@Entity
@Table(name = "PRENDA")
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_prenda;

    @Column(length = 50, nullable = false)
    private String nombre_prenda;

    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    private String talla;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 100, nullable = false)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_tipo_prenda", referencedColumnName = "id_tipo_prenda")
    private TipoPrenda id_tipo_Prenda;
}
