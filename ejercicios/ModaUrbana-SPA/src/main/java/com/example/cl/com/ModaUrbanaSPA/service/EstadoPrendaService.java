package com.example.cl.com.ModaUrbanaSPA.service;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.EstadoPrendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstadoPrendaService {

    @Autowired
    private EstadoPrendaRepository estadoPrendaRepositorio;

    public List<EstadoPrenda> findAll() {
        return estadoPrendaRepositorio.findAll();
    }

    public EstadoPrenda findById(Integer id) {
        return estadoPrendaRepositorio.findById(id).orElse(null);
    }

    public EstadoPrenda save(EstadoPrenda estadoPrenda) {
        return estadoPrendaRepositorio.save(estadoPrenda);
    }

    public void deleteById(Integer id) {
        estadoPrendaRepositorio.deleteById(id);
    }
}