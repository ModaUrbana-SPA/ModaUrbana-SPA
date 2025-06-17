package com.example.cl.com.ModaUrbana_SPA.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.EstadoPrendaService;
import com.example.cl.com.ModaUrbanaSPA.controller.EstadoPrendaController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import org.springframework.http.MediaType;

@WebMvcTest(EstadoPrendaController.class)
public class EstadoPrendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstadoPrendaService estadoPrendaService;

    @Autowired
    private ObjectMapper objectMapper;

    private EstadoPrenda estadoPrenda;

    @BeforeEach
    void setUp() {
        estadoPrenda = new EstadoPrenda();
        estadoPrenda.setId_estado_prenda(1);
        estadoPrenda.setHoraLlegada(LocalDateTime.now());
        estadoPrenda.setEstado("Disponible"); 
    }

    @Test
    public void testGetAllEstados() throws Exception {
        when(estadoPrendaService.findAll()).thenReturn(List.of(estadoPrenda));

        mockMvc.perform(get("/api/estado-prendas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].hora llegada").value(LocalDateTime.now().toString()))
                .andExpect(jsonPath("$[0].estado").value("Disponible"));
    }

    @Test
    public void testGetEstadoPrendarbyId() throws Exception {
        when(estadoPrendaService.findEstadoById(1)).thenReturn(estadoPrenda);

        mockMvc.perform(get("/api/estado-prenda/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].hora llegada").value(LocalDateTime.now().toString()))
                .andExpect(jsonPath("$[0].estado").value("Disponible"));
    }

    @Test
    public void testCreateEstadoPrenda() throws Exception {
        when(estadoPrendaService.createEstado(any(EstadoPrenda.class))).thenReturn(estadoPrenda);

        mockMvc.perform(post("/api/estado-prenda")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(estadoPrenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].hora llegada").value(LocalDateTime.now().toString()))
                .andExpect(jsonPath("$[0].estado").value("Disponible"));
    }

    @Test
    public void testUpdateEstadoPrenda() throws Exception {
        when(estadoPrendaService.createEstado(any(EstadoPrenda.class))).thenReturn(estadoPrenda);

        mockMvc.perform(put("/api/estado-prenda/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(estadoPrenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].hora llegada").value(LocalDateTime.now().toString()))
                .andExpect(jsonPath("$[0].estado").value("Disponible"));
    }

    @Test
    public void testEliminar() throws Exception {
        doNothing().when(estadoPrendaService).deleteById(1);

        mockMvc.perform(delete("/api/estado-prenda/1"))
                .andExpect(status().isOk());
    
        verify(estadoPrendaService, times(1)).deleteById(1);

    }
}

