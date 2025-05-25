package com.example.cl.com.ModaUrbana_SPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cl.com.ModaUrbana_SPA.model.Prenda;

@Repository
public interface PrendaRepositorio extends JpaRepository<Prenda, Long> {

}
