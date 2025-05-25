package com.example.cl.com.ModaUrbana_SPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.cl.com.ModaUrbana_SPA.model.Prenda;
import com.example.cl.com.ModaUrbana_SPA.service.PrendaService;

import org.springframework.web.bind.annotation.*;

@RestController
public class PrendaController {

    @Autowired
    private PrendaService prendaService;

    // Metodo Get
    @GetMapping
    public ResponseEntity<List<Prenda>> listar() {
        List<Prenda> prendas = prendaService.fetchAll();

        if (prendas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenda> buscar(@PathVariable Long id) {
        Prenda prenda = prendaService.fetchById(id);
        return ResponseEntity.ok(prenda);
    }

    @PostMapping
    public ResponseEntity<Prenda> guardar(@RequestBody Prenda prenda) {
        Prenda nuevaPrenda = prendaService.save(prenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPrenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prenda> actualizar(@PathVariable Long id, @RequestBody Prenda prenda) {
        Prenda existente = prendaService.fetchById(id);

        existente.setNombre(prenda.getNombre());
        existente.setPrecio(prenda.getPrecio());
        existente.setStock(prenda.getStock());
        existente.setTalla(prenda.getTalla());
        existente.setColor(prenda.getColor());

        prendaService.save(existente);
        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        prendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
