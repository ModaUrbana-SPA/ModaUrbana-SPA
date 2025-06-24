package com.example.cl.com.ModaUrbanaSPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.repository.PrendaRepositoriy;

@Service
public class PrendaService {

    @Autowired
    private PrendaRepositoriy prendaRepository;

    public List<Prenda> findAll() {
        return prendaRepository.findAll();
    }   

    public Prenda findById(Integer id) {
        return prendaRepository.findById(id).orElse(null);
    }   

    public Prenda save(Prenda prenda) {
        return prendaRepository.save(prenda);
    }

    public void deleteById(Integer id) {
        prendaRepository.deleteById(id);
    }
}
