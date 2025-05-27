package com.example.cl.com.ModaUrbanaSPA.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("POLERON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poleron extends Prenda {
    @Column(nullable = false)
    private boolean tienecapucha;
    @Column(nullable = false)
    private boolean tieneEstampado;

}
