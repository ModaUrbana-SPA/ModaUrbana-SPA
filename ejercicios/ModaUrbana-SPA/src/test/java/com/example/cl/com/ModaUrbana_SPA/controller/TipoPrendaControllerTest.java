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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.TipoPrendaService;
import com.example.cl.com.ModaUrbanaSPA.controller.TipoPrendaController;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TipoPrendaController.class)
public class TipoPrendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoPrendaService tipoPrendaService;

    @Autowired
    private ObjectMapper objectMapper;

    private TipoPrenda tipoPrenda;

    @BeforeEach
    void setUp() {
        tipoPrenda = new TipoPrenda();
        tipoPrenda.setId_tipo_prenda(1);
        tipoPrenda.setNombre("Camisas");
        tipoPrenda.setDescripcion("Tipos de camisas formales y casuales");
    }

    @Test
    public void testGetAllTipoPrendas() throws Exception {
        when(tipoPrendaService.findAll()).thenReturn(List.of(tipoPrenda));

        mockMvc.perform(get("/api/tipoPrendas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_tipo_prenda").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Camisas"))
                .andExpect(jsonPath("$[0].descripcion").value("Tipos de camisas formales y casuales"));
    }

    @Test
    public void testGetTipoPrendaById() throws Exception {
        when(tipoPrendaService.findById(1)).thenReturn(tipoPrenda);

        mockMvc.perform(get("/api/tipoPrendas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_tipo_prenda").value(1))
                .andExpect(jsonPath("$.nombre").value("Camisas"))
                .andExpect(jsonPath("$.descripcion").value("Tipos de camisas formales y casuales"));
    }

    @Test
    public void testCreateTipoPrenda() throws Exception {
        when(tipoPrendaService.save(any(TipoPrenda.class))).thenReturn(tipoPrenda);

        mockMvc.perform(post("/api/tipoPrendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoPrenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_tipo_prenda").value(1))
                .andExpect(jsonPath("$.nombre").value("Camisas"))
                .andExpect(jsonPath("$.descripcion").value("Tipos de camisas formales y casuales"));
    }

    @Test
    public void testUpdateTipoPrenda() throws Exception {
        when(tipoPrendaService.save(any(TipoPrenda.class))).thenReturn(tipoPrenda);

        mockMvc.perform(put("/api/tipoPrendas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoPrenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_tipo_prenda").value(1))
                .andExpect(jsonPath("$.nombre").value("Camisas"))
                .andExpect(jsonPath("$.descripcion").value("Tipos de camisas formales y casuales"));
    }

    @Test
    public void testDeleteTipoPrenda() throws Exception {
        doNothing().when(tipoPrendaService).deleteById(1);

        mockMvc.perform(delete("/api/tipoPrendas/1"))
                .andExpect(status().isOk());

        verify(tipoPrendaService, times(1)).deleteById(1);
    }
}
