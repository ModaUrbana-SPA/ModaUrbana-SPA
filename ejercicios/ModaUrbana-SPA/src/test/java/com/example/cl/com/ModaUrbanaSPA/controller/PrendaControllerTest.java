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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cl.com.ModaUrbanaSPA.config.JwtUtil;
import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.service.PrendaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PrendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrendaService prendaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Prenda prenda;

    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setUp() {
        token = jwtUtil.generateToken("PepeTest", "test");

        TipoPrenda tipo = new TipoPrenda();
        tipo.setId_tipo_prenda(1);
        tipo.setNombre("Polera");
        tipo.setDescripcion("Prenda superior ligera de manga corta");

        EstadoPrenda estadoPrenda = new EstadoPrenda();
        estadoPrenda.setId_estado_prenda(1);
        estadoPrenda.setEstado("Disponible");

        prenda = new Prenda();
        prenda.setId_prenda(10);
        prenda.setNombre_prenda("Camisa formal caballero");
        prenda.setPrecio(25000);
        prenda.setImagen("MONO_HOODIE_ESTAMPADO.PNG");
        prenda.setColor("Negro");
        prenda.setTalla("L");
        prenda.setStock(10);
        prenda.setDescripcTipoPrenda(tipo);
        prenda.setEstadoPrenda(estadoPrenda);
    }

    @Test
    public void testGetAllPrendas() throws Exception {
        when(prendaService.findAll()).thenReturn(List.of(prenda));

        mockMvc.perform(get("/api/prendas")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_prenda").value(10))
                .andExpect(jsonPath("$[0].nombre_prenda").value("Camisa formal caballero"))
                .andExpect(jsonPath("$[0].precio").value(25000))
                .andExpect(jsonPath("$[0].imagen").value("MONO_HOODIE_ESTAMPADO.PNG"))
                .andExpect(jsonPath("$[0].color").value("Negro"))
                .andExpect(jsonPath("$[0].talla").value("L"))
                .andExpect(jsonPath("$[0].stock").value(10))
                .andExpect(jsonPath("$[0].descripcTipoPrenda.nombre").value("Polera"))
                .andExpect(jsonPath("$[0].estadoPrenda.estado").value("Disponible"));
    }

    @Test
    public void testGetPrendaById() throws Exception {
        when(prendaService.findById(10)).thenReturn(prenda);

        mockMvc.perform(get("/api/prendas/10")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_prenda").value(10))
                .andExpect(jsonPath("$.nombre_prenda").value("Camisa formal caballero"))
                .andExpect(jsonPath("$.precio").value(25000))
                .andExpect(jsonPath("$.imagen").value("MONO_HOODIE_ESTAMPADO.PNG"))
                .andExpect(jsonPath("$.color").value("Negro"))
                .andExpect(jsonPath("$.talla").value("L"))
                .andExpect(jsonPath("$.stock").value(10))
                .andExpect(jsonPath("$.descripcTipoPrenda.nombre").value("Polera"))
                .andExpect(jsonPath("$.estadoPrenda.estado").value("Disponible"));
    }

    @Test
    public void testCreatePrenda() throws Exception {
        when(prendaService.save(any(Prenda.class))).thenReturn(prenda);

        mockMvc.perform(post("/api/prendas")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prenda)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id_prenda").value(10))
                .andExpect(jsonPath("$.nombre_prenda").value("Camisa formal caballero"))
                .andExpect(jsonPath("$.precio").value(25000))
                .andExpect(jsonPath("$.imagen").value("MONO_HOODIE_ESTAMPADO.PNG"))
                .andExpect(jsonPath("$.color").value("Negro"))
                .andExpect(jsonPath("$.talla").value("L"))
                .andExpect(jsonPath("$.stock").value(10))
                .andExpect(jsonPath("$.descripcTipoPrenda.nombre").value("Polera"))
                .andExpect(jsonPath("$.estadoPrenda.estado").value("Disponible"));
    }

    @Test
    public void testUpdatePrenda() throws Exception {
        when(prendaService.findById(10)).thenReturn(prenda); // <- AGREGADO
        when(prendaService.save(any(Prenda.class))).thenReturn(prenda);

        mockMvc.perform(put("/api/prendas/10")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(prenda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_prenda").value(10))
                .andExpect(jsonPath("$.nombre_prenda").value("Camisa formal caballero"))
                .andExpect(jsonPath("$.precio").value(25000))
                .andExpect(jsonPath("$.imagen").value("MONO_HOODIE_ESTAMPADO.PNG"))
                .andExpect(jsonPath("$.color").value("Negro"))
                .andExpect(jsonPath("$.talla").value("L"))
                .andExpect(jsonPath("$.stock").value(10))
                .andExpect(jsonPath("$.descripcTipoPrenda.nombre").value("Polera"))
                .andExpect(jsonPath("$.estadoPrenda.estado").value("Disponible"));
    }

    @Test
    public void testDeletePrenda() throws Exception {
        doNothing().when(prendaService).deleteById(10);

        mockMvc.perform(delete("/api/prendas/10")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());

        verify(prendaService, times(1)).deleteById(10);
    }
}
