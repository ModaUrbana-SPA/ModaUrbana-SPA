package com.example.cl.com.ModaUrbanaSPA.controller;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.EstadoPrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estado-prenda")
public class EstadoPrendaController {

    @Autowired
    private EstadoPrendaService estadoPrendaService;

    @GetMapping
    public ResponseEntity<List<EstadoPrenda>> listar() {
        List<EstadoPrenda> estados = estadoPrendaService.findAll();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoPrenda> Buscar(@PathVariable Integer id) {
        EstadoPrenda estado = estadoPrendaService.fetchById(id);
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public EstadoPrenda createEstado(@RequestBody EstadoPrenda estadoPrenda) {
        return estadoPrendaService.createEstado(estadoPrenda);
    }

    @PutMapping("/{id}")
    public EstadoPrenda updateEstado(@PathVariable Integer id, @RequestBody EstadoPrenda estadoPrenda) {
        EstadoPrenda existingEstado = estadoPrendaService.getEstadoById(id);
        if (existingEstado != null) {
            existingEstado.setHoraLlegada(estadoPrenda.getHoraLlegada());
            existingEstado.setEstado(estadoPrenda.getEstado());
            return estadoPrendaService.createEstado(existingEstado);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Integer id) {
        boolean deleted = estadoPrendaService.deleteEstado(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}