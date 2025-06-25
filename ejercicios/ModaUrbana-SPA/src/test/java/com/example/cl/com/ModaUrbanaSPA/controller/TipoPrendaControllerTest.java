package com.example.cl.com.ModaUrbanaSPA.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.cl.com.ModaUrbanaSPA.config.JwtUtil;
import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.TipoPrendaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TipoPrendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoPrendaService tipoPrendaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    private TipoPrenda tipoPrenda;
    private String token;

    @BeforeEach
    void setUp() {
        token = jwtUtil.generateToken("PepeTest", "test");

        tipoPrenda = new TipoPrenda();
        tipoPrenda.setId_tipo_prenda(6);
        tipoPrenda.setNombre("Camisa");
        tipoPrenda.setDescripcion("Prenda superior formal, generalmente con cuello y botones");
    }

    @Test
    public void testGetAllTipoPrendas() throws Exception {
        when(tipoPrendaService.findAll()).thenReturn(List.of(tipoPrenda));

        mockMvc.perform(get("/api/tipo-prendas")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_tipo_prenda").value(6))
                .andExpect(jsonPath("$[0].nombre").value("Camisa"))
                .andExpect(jsonPath("$[0].descripcion")
                        .value("Prenda superior formal, generalmente con cuello y botones"));
    }

    @Test
    public void testGetTipoPrendaById() throws Exception {
        when(tipoPrendaService.findById(6)).thenReturn(tipoPrenda);

        mockMvc.perform(get("/api/tipo-prendas/6")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_tipo_prenda").value(6))
                .andExpect(jsonPath("$.nombre").value("Camisa"))
                .andExpect(
                        jsonPath("$.descripcion").value("Prenda superior formal, generalmente con cuello y botones"));
    }

    @Test
    public void testCreateTipoPrenda() throws Exception {
        when(tipoPrendaService.save(any(TipoPrenda.class))).thenReturn(tipoPrenda);

        mockMvc.perform(post("/api/tipo-prendas")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoPrenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_tipo_prenda").value(6))
                .andExpect(jsonPath("$.nombre").value("Camisa"))
                .andExpect(
                        jsonPath("$.descripcion").value("Prenda superior formal, generalmente con cuello y botones"));
    }

    @Test
    public void testUpdateTipoPrenda() throws Exception {
        when(tipoPrendaService.findById(6)).thenReturn(tipoPrenda);
        when(tipoPrendaService.save(any(TipoPrenda.class))).thenReturn(tipoPrenda);

        mockMvc.perform(put("/api/tipo-prendas/6")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tipoPrenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_tipo_prenda").value(6))
                .andExpect(jsonPath("$.nombre").value("Camisa"))
                .andExpect(
                        jsonPath("$.descripcion").value("Prenda superior formal, generalmente con cuello y botones"));
    }

    @Test
    public void testDeleteTipoPrenda() throws Exception {
        doNothing().when(tipoPrendaService).deleteById(6);

        mockMvc.perform(delete("/api/tipo-prendas/6")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        verify(tipoPrendaService, times(1)).deleteById(6);
    }
}
