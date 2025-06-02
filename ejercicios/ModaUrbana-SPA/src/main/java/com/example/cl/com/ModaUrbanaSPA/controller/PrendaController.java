package com.example.cl.com.ModaUrbanaSPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.service.PrendaService;

@RestController
@RequestMapping("/prendas")
public class PrendaController {

    @Autowired
    private PrendaService prendaService;

    // Método GET actualizado
    @GetMapping
    public ResponseEntity<List<Prenda>> listar() {
        List<Prenda> prendas = prendaService.fetchAll();
        // Elimina el 404 en caso de lista vacía
        return ResponseEntity.ok(prendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenda> buscar(@PathVariable Long id) {
        Prenda prenda = prendaService.fetchById(id);
        return ResponseEntity.ok(prenda);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Prenda prenda) {
        try {
            Prenda nuevaPrenda = prendaService.save(prenda);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPrenda);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 Esto mostrará la causa del error en consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar prenda: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prenda> actualizar(@PathVariable Long id, @RequestBody Prenda prenda) {
        Prenda existente = prendaService.fetchById(id);
        existente.setNombre(prenda.getNombre());
        existente.setPrecio(prenda.getPrecio());
        existente.setStock(prenda.getStock());
        existente.setTalla(prenda.getTalla());
        existente.setColor(prenda.getColor());
        existente.setImagen(prenda.getImagen());
        existente.setDestacado(prenda.isDestacado());

        prendaService.save(existente);
        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        prendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

// chupala ro B)