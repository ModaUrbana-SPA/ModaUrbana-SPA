package com.example.cl.com.ModaUrbanaSPA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIPO_PRENDA")
public class TipoPrenda {
    @Id
    @Column(nullable = false)
    private Integer id_tipo_prenda;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column (length = 155 , nullable = false)
    private String descripcion;
}
