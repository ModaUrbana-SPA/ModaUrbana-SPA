package com.example.cl.com.ModaUrbanaSPA.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ESTADO_PRENDA")
public class EstadoPrenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_estado_prenda;

    @Column(nullable = false)
    private LocalDateTime hora_llegada;

    @Column(length = 50, nullable = false)
    private String estado;
}
