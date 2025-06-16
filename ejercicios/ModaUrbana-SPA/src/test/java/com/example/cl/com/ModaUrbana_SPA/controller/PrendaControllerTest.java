package com.example.cl.com.ModaUrbana_SPA.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cl.com.ModaUrbanaSPA.controller.PrendaController;
import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.service.PrendaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PrendaController.class)
public class PrendaControllerTest {
    

    @Autowired
    private MockMvc mockMvc; 

    @MockBean
    private PrendaService prendaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Prenda prenda;
    
    
    @BeforeEach
    void setUp() {

        prenda = new Prenda();
        prenda.setId_prenda(    1);
        prenda.setNombre_prenda("Camisa formal caballero");
        prenda.setPrecio(25000);
        prenda.setImagen("MONO_HOODIE_ESTAMPADO.PNG " );
        prenda.setColor("Negro");
        prenda.setTalla("L"); ;
        prenda.setDescripcTipoPrenda(null); // Asignar un valor válido o null según tu lógica
        prenda.setEstadoPrenda(null); // Asignar un valor válido o null según tu lógica
    }
}
