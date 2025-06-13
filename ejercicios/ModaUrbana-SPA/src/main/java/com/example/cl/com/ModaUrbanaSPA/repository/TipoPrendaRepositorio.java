package com.example.cl.com.ModaUrbanaSPA.repository;

import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPrendaRepositorio extends JpaRepository<TipoPrenda, Long> {
}