package com.example.cl.com.ModaUrbanaSPA.service;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.EstadoPrendaRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstadoPrendaService {

    @Autowired
    private EstadoPrendaRepositoriy estadoPrendaRepository;

    public List<EstadoPrenda> findAll() {
        return estadoPrendaRepository.findAll();
    }

    public EstadoPrenda findById(Integer id) {
        return estadoPrendaRepository.findById(id).orElse(null);
    }

    public EstadoPrenda save(EstadoPrenda estadoPrenda) {
        return estadoPrendaRepository.save(estadoPrenda);
    }

    public void deleteById(Integer id) {
        estadoPrendaRepository.deleteById(id);
    }
}