package com.example.cl.com.ModaUrbanaSPA.ensamblador;

import com.example.cl.com.ModaUrbanaSPA.controller.PrendaControllerV2;
import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PrendaModelAssembler implements RepresentationModelAssembler<Prenda, EntityModel<Prenda>> {

    @Override
    public EntityModel<Prenda> toModel(Prenda prenda) {
        return EntityModel.of(prenda,
                linkTo(methodOn(PrendaControllerV2.class).getPrendabyId(prenda.getId_prenda())).withSelfRel(),
                linkTo(methodOn(PrendaControllerV2.class).getlistarTodas()).withRel("prendas"),
                linkTo(methodOn(PrendaControllerV2.class).actualizar(prenda.getId_prenda(), prenda))
                        .withRel("actualizar"),
                linkTo(methodOn(PrendaControllerV2.class).deletePrenda(prenda.getId_prenda())).withRel("eliminar"));

    }
}
