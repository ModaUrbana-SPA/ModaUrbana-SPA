package com.example.cl.com.ModaUrbanaSPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.service.PrendaService;

@RestController
@RequestMapping("/api/prendas")
public class PrendaController {

    @Autowired
    private PrendaService prendaService;

    // Método GET actualizado
    @GetMapping
    public ResponseEntity<List<Prenda>> listar() {
        List<Prenda> prendas = prendaService.findAll();
        // Elimina el 404 en caso de lista vacía
        return ResponseEntity.ok(prendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenda> buscar(@PathVariable Integer id) {
        Prenda prenda = prendaService.findById(id);
        return ResponseEntity.ok(prenda);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Prenda prenda) {
        try {
            Prenda nuevaPrenda = prendaService.save(prenda);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPrenda);
        } catch (Exception e) {
            e.printStackTrace(); // Esto mostrará la causa del error en consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar prenda: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prenda> actualizar(@PathVariable Integer id, @RequestBody Prenda prenda) {
        Prenda existente = prendaService.findById(id);
        existente.setNombre_prenda(prenda.getNombre_prenda());
        existente.setPrecio(prenda.getPrecio());
        existente.setDescripcTipoPrenda(prenda.getDescripcTipoPrenda());
        existente.setStock(prenda.getStock());
        existente.setTalla(prenda.getTalla());
        existente.setColor(prenda.getColor());
        existente.setImagen(prenda.getImagen());
        existente.setEstadoPrenda(prenda.getEstadoPrenda());
        prendaService.save(existente);
        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        prendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
