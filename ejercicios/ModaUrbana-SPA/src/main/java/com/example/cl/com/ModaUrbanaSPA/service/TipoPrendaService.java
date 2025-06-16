package com.example.cl.com.ModaUrbanaSPA.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;

@Service
public class TipoPrendaService {

    private final List<TipoPrenda> tipoPrendas = new ArrayList<>();
    private long nextId = 1;

    public List<TipoPrenda> fetchAll() {
        return new ArrayList<>(tipoPrendas);
    }

    public TipoPrenda fetchById(Long id) {
        Optional<TipoPrenda> tipoPrenda = tipoPrendas.stream()
                .filter(tp -> tp.getId().equals(id))
                .findFirst();
        return tipoPrenda.orElse(null);
    }

    public TipoPrenda save(TipoPrenda tipoPrenda) {
        if (tipoPrenda.getId() == null) {
            tipoPrenda.setId(nextId++);
            tipoPrendas.add(tipoPrenda);
        } else {
            TipoPrenda existente = fetchById(tipoPrenda.getId());
            if (existente != null) {
                tipoPrendas.remove(existente);
            }
            tipoPrendas.add(tipoPrenda);
        }
        return tipoPrenda;
    }

    public void delete(Long id) {
        tipoPrendas.removeIf(tp -> tp.getId().equals(id));
    }
}

