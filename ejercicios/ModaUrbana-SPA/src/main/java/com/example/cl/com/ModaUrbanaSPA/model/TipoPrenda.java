package com.example.cl.com.ModaUrbanaSPA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
// DATA
@Entity
@Table(name = "TIPO_PRENDA")
public class TipoPrenda {
    @Id
    @Column(nullable = false)
    private Integer id_tipo_prenda;

    @Column(length = 50, nullable = false)
    private String nombre;

    private Long id;
    // other fields, constructors, etc.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
