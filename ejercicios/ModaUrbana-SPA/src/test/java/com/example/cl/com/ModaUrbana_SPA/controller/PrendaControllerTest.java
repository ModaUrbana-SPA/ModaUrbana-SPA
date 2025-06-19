package com.example.cl.com.ModaUrbana_SPA.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cl.com.ModaUrbanaSPA.controller.PrendaController;
import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.service.PrendaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

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
        prenda.setId_prenda(1);
        prenda.setNombre_prenda("Camisa formal caballero");
        prenda.setPrecio(25000);
        prenda.setImagen("MONO_HOODIE_ESTAMPADO.PNG ");
        prenda.setColor("Negro");
        prenda.setTalla("L");
        prenda.setDescripcTipoPrenda(null); // Asignar un valor válido o null según tu lógica
        prenda.setEstadoPrenda(null); // Asignar un valor válido o null según tu lógica
    }

    // Listar todas las prendas
    @Test
    public void testGetAllPrendas() throws Exception {
        when(prendaService.findAll()).thenReturn(List.of(prenda));

        mockMvc.perform(get("/api/prendas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_prenda").value(1))
                .andExpect(jsonPath("$[0].nombre_prenda").value("Camisa formal caballero"))
                .andExpect(jsonPath("$[0].precio").value(25000))
                .andExpect(jsonPath("$[0].imagen").value("MONO_HOODIE_ESTAMPADO.PNG "))
                .andExpect(jsonPath("$[0].color").value("Negro"))
                .andExpect(jsonPath("$[0].talla").value("L"))
                .andExpect(jsonPath("$[0].descripcTipoPrenda").doesNotExist())
                .andExpect(jsonPath("$[0].estadoPrenda").doesNotExist());
    }

    // Buscar una prenda por ID
    @Test
    public void testGetPrendaById() throws Exception {
        when(prendaService.findById(1)).thenReturn(prenda);

        mockMvc.perform(get("/api/prendas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_prenda").value(1))
                .andExpect(jsonPath("$.nombre_prenda").value("Camisa formal caballero"))
                .andExpect(jsonPath("$.precio").value(25000))
                .andExpect(jsonPath("$.imagen").value("MONO_HOODIE_ESTAMPADO.PNG "))
                .andExpect(jsonPath("$.color").value("Negro"))
                .andExpect(jsonPath("$[0].talla").value("L"))
                .andExpect(jsonPath("$[0].descripcTipoPrenda").doesNotExist())
                .andExpect(jsonPath("$[0].estadoPrenda").doesNotExist());
    }

    // Crear una nueva prenda
    @Test
    public void testCreatePrenda() throws Exception {
        when(prendaService.save(any(Prenda.class))).thenReturn(prenda);

        mockMvc.perform(post("/api/prendas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_prenda").value(1))
                .andExpect(jsonPath("$.nombre_prenda").value("Camisa formal caballero"))
                .andExpect(jsonPath("$.precio").value(25000))
                .andExpect(jsonPath("$.imagen").value("MONO_HOODIE_ESTAMPADO.PNG "))
                .andExpect(jsonPath("$.color").value("Negro"))
                .andExpect(jsonPath("$.talla").value("L"))
                .andExpect(jsonPath("$.descripcTipoPrenda").doesNotExist())
                .andExpect(jsonPath("$.estadoPrenda").doesNotExist());
    }

    // Actualizar una prenda existente
    @Test
    public void testUpdatePrenda() throws Exception {
        when(prendaService.save(any(Prenda.class))).thenReturn(prenda);

        mockMvc.perform(put("/api/prendas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_prenda").value(1))
                .andExpect(jsonPath("$.nombre_prenda").value("Camisa formal caballero"))
                .andExpect(jsonPath("$.precio").value(25000))
                .andExpect(jsonPath("$.imagen").value("MONO_HOODIE_ESTAMPADO.PNG "))
                .andExpect(jsonPath("$.color").value("Negro"))
                .andExpect(jsonPath("$.talla").value("L"))
                .andExpect(jsonPath("$.descripcTipoPrenda").doesNotExist())
                .andExpect(jsonPath("$.estadoPrenda").doesNotExist());
    }

    // Eliminar una prenda por ID
    @Test
    public void testDeletePrenda() throws Exception {
        doNothing().when(prendaService).deleteById(1); // por qué si pongo id:1 no funciona?

        mockMvc.perform(delete("/api/prendas/1"))
                .andExpect(status().isOk());

        verify(prendaService, times(1)).deleteById(1);
    }

}