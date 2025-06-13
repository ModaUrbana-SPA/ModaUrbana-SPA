package com.example.cl.com.ModaUrbanaSPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.TipoPrendaService;

@RestController
@RequestMapping("/api/tipo-prenda")
public class TipoPrendaController {

    @Autowired
    private TipoPrendaService tipoPrendaService;

    @GetMapping
    public List<TipoPrenda> getAllTipoPrendas() {
        return tipoPrendaService.getAllTipoPrendas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPrenda> getTipoPrendaById(@PathVariable Long id) {
        TipoPrenda tipoPrenda = tipoPrendaService.getTipoPrendaById(id);
        if (tipoPrenda != null) {
            return ResponseEntity.ok(tipoPrenda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public TipoPrenda createTipoPrenda(@RequestBody TipoPrenda tipoPrenda) {
        return tipoPrendaService.createTipoPrenda(tipoPrenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPrenda> updateTipoPrenda(@PathVariable Long id, @RequestBody TipoPrenda tipoPrenda) {
        TipoPrenda updated = tipoPrendaService.updateTipoPrenda(id, tipoPrenda);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoPrenda(@PathVariable Long id) {
        boolean deleted = tipoPrendaService.deleteTipoPrenda(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}