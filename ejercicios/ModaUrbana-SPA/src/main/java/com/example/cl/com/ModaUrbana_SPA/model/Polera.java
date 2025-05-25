package com.example.cl.com.ModaUrbana_SPA.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("POLERA")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Polera extends Prenda {
    @Column(nullable = false)
    private boolean tieneEstampado;
    @Column(nullable = false)
    private boolean tieneManga;
}
