package com.example.cl.com.ModaUrbanaSPA.controller;

import com.example.cl.com.ModaUrbanaSPA.ensamblador.PrendaModelAssembler;
import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.repository.PrendaRepositorio;
import com.example.cl.com.ModaUrbanaSPA.service.PrendaService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @GetMapping(value ="/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Prenda> getPrendabyId(@PathVariable Integer id) {
        Prenda prenda = prendaservice.findById(id);
        return assembler.toModel(prenda);
    }

    @GetMapping("/tipo/{id}")
    public CollectionModel<EntityModel<Prenda>> buscarPorTipo(@PathVariable String tipo) {
        List<EntityModel<Prenda>> prendas = repository
                .findByDescripcTipoPrendaDescripcionIgnoreCase(tipo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(prendas,
                linkTo(methodOn(PrendaControllerV2.class).buscarPorTipo(tipo)).withSelfRel());
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Prenda>> postPrenda(@RequestBody  Prenda prenda) {
        Prenda newPrenda = prendaservice.save(prenda);
        
        return ResponseEntity
                .created(linkTo(methodOn(PrendaControllerV2.class).getPrendabyId(newPrenda.getId_prenda())).toUri())
                .body(assembler.toModel(newPrenda));
    }

    
}
