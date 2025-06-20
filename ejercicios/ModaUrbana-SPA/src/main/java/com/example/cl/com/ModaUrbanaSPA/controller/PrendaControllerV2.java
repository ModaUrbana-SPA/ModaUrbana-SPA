package com.example.cl.com.ModaUrbanaSPA.controller;

import com.example.cl.com.ModaUrbanaSPA.ensamblador.PrendaModelAssembler;
import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.service.PrendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/prendas")
public class PrendaControllerV2 {

    @Autowired
    private PrendaService prendaservice;

    @Autowired
    private PrendaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Prenda>> getlistarTodas() {
        List<EntityModel<Prenda>> prendas = prendaservice.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(prendas,
                linkTo(methodOn(PrendaControllerV2.class).getlistarTodas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Prenda> getPrendabyId(@PathVariable Integer id) {
        Prenda prenda = prendaservice.findById(id);
        return assembler.toModel(prenda);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Prenda>> save(@RequestBody Prenda prenda) {
        Prenda newPrenda = prendaservice.save(prenda);

        return ResponseEntity
                .created(linkTo(methodOn(PrendaControllerV2.class).getPrendabyId(newPrenda.getId_prenda())).toUri())
                .body(assembler.toModel(newPrenda));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Prenda>> actualizar(@PathVariable Integer id, @RequestBody Prenda prenda) {

        Prenda existente = prendaservice.findById(id);
        existente.setNombre_prenda(prenda.getNombre_prenda());
        existente.setPrecio(prenda.getPrecio());
        existente.setDescripcTipoPrenda(prenda.getDescripcTipoPrenda());
        existente.setTalla(prenda.getTalla());
        existente.setColor(prenda.getColor());
        existente.setImagen(prenda.getImagen());
        existente.setEstadoPrenda(prenda.getEstadoPrenda());

        Prenda actualizada = prendaservice.save(existente);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenda(@PathVariable Integer id) {
        prendaservice.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
