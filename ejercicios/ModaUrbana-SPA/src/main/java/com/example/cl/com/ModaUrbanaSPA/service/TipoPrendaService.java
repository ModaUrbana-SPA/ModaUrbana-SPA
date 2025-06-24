package com.example.cl.com.ModaUrbanaSPA.service;

import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.TipoPrendaRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPrendaService {
    @Autowired
    private TipoPrendaRepositoriy tipoPrendaRepository;

    public List<TipoPrenda> findAll() {
        return tipoPrendaRepository.findAll();
    }

    public TipoPrenda findById(Integer id) {
        return tipoPrendaRepository.findById(id).orElse(null);
    }

    public TipoPrenda save(TipoPrenda tipoPrenda) {
        return tipoPrendaRepository.save(tipoPrenda);
    }

    public void deleteById(Integer id) {
        tipoPrendaRepository.deleteById(id);
    }
}

