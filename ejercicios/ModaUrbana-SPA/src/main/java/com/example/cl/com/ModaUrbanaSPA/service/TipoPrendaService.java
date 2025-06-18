package com.example.cl.com.ModaUrbanaSPA.service;

import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.TipoPrendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPrendaService {
    @Autowired
    private TipoPrendaRepositorio tipoPrendaRepositorio;

    public List<TipoPrenda> findAll() {
        return tipoPrendaRepositorio.findAll();
    }

    public TipoPrenda findById(Integer id) {
        return tipoPrendaRepositorio.findById(id).orElse(null);
    }

    public TipoPrenda save(TipoPrenda tipoPrenda) {
        return tipoPrendaRepositorio.save(tipoPrenda);
    }

    public void deleteById(Integer id) {
        tipoPrendaRepositorio.deleteById(id);
    }
}

