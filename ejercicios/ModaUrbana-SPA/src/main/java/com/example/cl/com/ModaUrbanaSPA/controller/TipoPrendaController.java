package com.example.cl.com.ModaUrbanaSPA.controller;

import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.TipoPrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tipo-prendas")
public class TipoPrendaController {

    @Autowired
    private TipoPrendaService tipoPrendaService;

    @GetMapping
    public List<TipoPrenda> getAllTipoPrendas() {
        return tipoPrendaService.findAll();
    }

    @GetMapping("/{id}")
    public TipoPrenda getTipoPrendaById(@PathVariable Integer id) {
        return tipoPrendaService.findById(id);
    }

    @PostMapping
    public TipoPrenda createTipoPrenda(@RequestBody TipoPrenda tipoPrenda) {
        return tipoPrendaService.save(tipoPrenda);
    }

    @PutMapping("/{id}")
    public TipoPrenda updatTipoPrenda(@PathVariable Integer id, @RequestBody TipoPrenda tipoPrenda) {
        tipoPrenda.setId_tipo_prenda(id);
        return tipoPrendaService.save(tipoPrenda);
    }

    @DeleteMapping("/{id}")
    public void deleteTipoPrenda(@PathVariable Integer id) {
        tipoPrendaService.deleteById(id);
    }
}
