package com.example.cl.com.ModaUrbanaSPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.TipoPrendaService;

@RestController
@RequestMapping("/api/tipo-prenda")
public class TipoPrendaController {

    @Autowired
    private TipoPrendaService tipoPrendaService;

    @GetMapping
    public ResponseEntity<List<TipoPrenda>> listar() {
        List<TipoPrenda> tipoPrendas = tipoPrendaService.fetchAll();
        return ResponseEntity.ok(tipoPrendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPrenda> buscar(@PathVariable Long id) {
        TipoPrenda tipoPrenda = tipoPrendaService.fetchById(id);
        return ResponseEntity.ok(tipoPrenda);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody TipoPrenda tipoPrenda) {
        try {
            TipoPrenda nuevo = tipoPrendaService.save(tipoPrenda);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar tipo de prenda: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPrenda> actualizar(@PathVariable Long id, @RequestBody TipoPrenda tipoPrenda) {
        TipoPrenda existente = tipoPrendaService.fetchById(id);
        existente.setNombre(tipoPrenda.getNombre());
        existente.setDescripcion(tipoPrenda.getDescripcion());
        tipoPrendaService.save(existente);
        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        tipoPrendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
