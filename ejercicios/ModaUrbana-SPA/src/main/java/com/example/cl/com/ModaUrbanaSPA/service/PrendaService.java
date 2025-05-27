package com.example.cl.com.ModaUrbanaSPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.repository.PrendaRepositorio;

@Service
public class PrendaService {

    @Autowired
    private PrendaRepositorio prendaRepository;

    public List<Prenda> fetchAll() {
        return prendaRepository.findAll();
    }

    public Prenda fetchById(Long id) {
        return prendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenda no encontrada"));
    }

    public Prenda save(Prenda prenda) {
        return prendaRepository.save(prenda);
    }

    public void delete(Long id) {
        prendaRepository.deleteById(id);
    }
}
