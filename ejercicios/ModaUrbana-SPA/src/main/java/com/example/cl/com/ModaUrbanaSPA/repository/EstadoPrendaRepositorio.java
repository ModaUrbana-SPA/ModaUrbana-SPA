package com.example.cl.com.ModaUrbanaSPA.repository;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EstadoPrendaRepositorio extends JpaRepository<EstadoPrenda, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas
}