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
    @Column(name = "id_estado_prenda")
    private Integer id; 

    @Column(name = "hora_llegada", nullable = false)
    private LocalDateTime horaLlegada;

    @Column(length = 50, nullable = false)
    private String estado;
}
