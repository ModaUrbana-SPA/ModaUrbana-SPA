package com.example.cl.com.ModaUrbanaSPA.controller;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.EstadoPrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/estado-prendas")
public class EstadoPrendaController {

    @Autowired
    private EstadoPrendaService estadoPrendaService;

    @GetMapping
    public List<EstadoPrenda> getAllEstadoPrendas() {
        return estadoPrendaService.findAll();
    }

    @GetMapping("/{id}")
    public EstadoPrenda getEstadoPrendaById(@PathVariable Integer id) {
        return estadoPrendaService.findById(id);
    }

    @PostMapping
    public EstadoPrenda createEstadoPrenda(@RequestBody EstadoPrenda estadoPrenda) {
        return estadoPrendaService.save(estadoPrenda);
    }

    @PutMapping("/{id}")
    public EstadoPrenda updateEstadoPrenda(@PathVariable Integer id, @RequestBody EstadoPrenda estadoPrenda) {
        estadoPrenda.setId_estado_prenda(id);
        return estadoPrendaService.save(estadoPrenda);
    }

    @DeleteMapping("/{id}")
    public void deleteEstadoPrenda(@PathVariable Integer id) {
        estadoPrendaService.deleteById(id);
    }

}