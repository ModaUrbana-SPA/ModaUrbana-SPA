package com.example.cl.com.ModaUrbanaSPA.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ESTADO_PRENDA")
public class EstadoPrenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_prenda")
    private Integer id_estado_prenda;

    @Column(length = 50, nullable = false)
    private String estado;
}
