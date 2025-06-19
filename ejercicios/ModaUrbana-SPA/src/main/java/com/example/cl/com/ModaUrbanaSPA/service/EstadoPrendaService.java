package com.example.cl.com.ModaUrbanaSPA.service;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.EstadoPrendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoPrendaService {

    @Autowired
    private EstadoPrendaRepositorio estadoPrendaRepositorio;

    public List<EstadoPrenda> findAll() {
        return estadoPrendaRepositorio.findAll();
    }

    public EstadoPrenda findEstadoById(Integer id) {
        Optional<EstadoPrenda> estado = estadoPrendaRepositorio.findById(id.longValue());
        return estado.orElse(null);
    }

    public EstadoPrenda createEstado(EstadoPrenda estadoPrenda) {
        return estadoPrendaRepositorio.save(estadoPrenda);
    }

    public EstadoPrenda updateEstado(Integer id, EstadoPrenda estadoPrenda) {
        if (estadoPrendaRepositorio.existsById(id.longValue())) {
            estadoPrenda.setId_estado_prenda(id);
            return estadoPrendaRepositorio.save(estadoPrenda);
        } else {
            return null;
        }
    }

    public boolean deleteEstado(Integer id) {
        if (estadoPrendaRepositorio.existsById(id.longValue())) {
            estadoPrendaRepositorio.deleteById(id.longValue());
            return true;
        } else {
            return false;
        }
    }
}