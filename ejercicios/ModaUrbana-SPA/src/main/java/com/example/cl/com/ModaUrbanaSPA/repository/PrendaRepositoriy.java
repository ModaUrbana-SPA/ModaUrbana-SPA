package com.example.cl.com.ModaUrbanaSPA.repository;

import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaRepositoriy extends JpaRepository<Prenda, Integer> {
}
