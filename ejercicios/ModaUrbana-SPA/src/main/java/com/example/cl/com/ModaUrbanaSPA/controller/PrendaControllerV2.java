package com.example.cl.com.ModaUrbanaSPA.controller;

import com.example.cl.com.ModaUrbanaSPA.ensamblador.PrendaModelAssembler;
import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.repository.PrendaRepositorio;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/prendas")
public class PrendaControllerV2 {

    private final PrendaRepositorio repository;
    private final PrendaModelAssembler assembler;

    public PrendaControllerV2(PrendaRepositorio repository, PrendaModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Prenda>> listarTodas() {
        List<EntityModel<Prenda>> prendas = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(prendas,
                linkTo(methodOn(PrendaControllerV2.class).listarTodas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Prenda> obtenerPorId(@PathVariable Integer id) {
        Prenda prenda = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la prenda con ID: " + id));
        return assembler.toModel(prenda);
    }

    @GetMapping("/tipo/{tipo}")
    public CollectionModel<EntityModel<Prenda>> buscarPorTipo(@PathVariable String tipo) {
        List<EntityModel<Prenda>> prendas = repository
                .findByDescripcTipoPrendaDescripcionIgnoreCase(tipo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(prendas,
                linkTo(methodOn(PrendaControllerV2.class).buscarPorTipo(tipo)).withSelfRel());
    }
}
