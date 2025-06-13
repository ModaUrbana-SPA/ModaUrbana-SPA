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

    public List<TipoPrenda> getAllTipoPrendas() {
        return new ArrayList<>(tipoPrendas);
    }

    public TipoPrenda getTipoPrendaById(Long id) {
        Optional<TipoPrenda> tipoPrenda = tipoPrendas.stream().filter(tp -> tp.getId().equals(id)).findFirst();
        return tipoPrenda.orElse(null);
    }

    public TipoPrenda createTipoPrenda(TipoPrenda tipoPrenda) {
        if (tipoPrenda.getId() == null) {
            tipoPrenda.setId(nextId++);
        }
        tipoPrendas.add(tipoPrenda);
        return tipoPrenda;
    }

    public TipoPrenda updateTipoPrenda(Long id, TipoPrenda tipoPrenda) {
        TipoPrenda existing = getTipoPrendaById(id);
        if (existing != null) {
            tipoPrendas.remove(existing);
            tipoPrenda.setId(id);
            tipoPrendas.add(tipoPrenda);
            return tipoPrenda;
        }
        return null;
    }

    public boolean deleteTipoPrenda(Long id) {
        return tipoPrendas.removeIf(tp -> tp.getId().equals(id));
    }
}
