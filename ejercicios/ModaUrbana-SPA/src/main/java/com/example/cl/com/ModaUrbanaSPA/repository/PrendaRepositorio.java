package com.example.cl.com.ModaUrbanaSPA.repository;

import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrendaRepositorio extends JpaRepository<Prenda, Integer> {
    List<Prenda> findByDescripcTipoPrendaDescripcionIgnoreCase(String descripcion);
}
