package com.example.cl.com.ModaUrbanaSPA.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_prenda")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo_prenda")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Polera.class, name = "POLERA"),
        @JsonSubTypes.Type(value = Poleron.class, name = "POLERON")
})

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
    @Column(nullable = false)
    private String imagen;
    @Column(nullable = false)
    private boolean destacado;
}
