package com.example.cl.com.ModaUrbana_SPA.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.EstadoPrendaRepositorio;
import com.example.cl.com.ModaUrbanaSPA.service.EstadoPrendaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EstadoPrendaServiceTest {

    @Autowired
    private EstadoPrendaService estadoPrendaService;

    @MockBean
    private EstadoPrendaRepositorio estadoPrendaRepositorio;

    // Listar todos los estados
    @Test
    public void testFindAll() {
        EstadoPrenda estado = new EstadoPrenda(1, LocalDateTime.now(), "Disponible");
        when(estadoPrendaRepositorio.findAll()).thenReturn(List.of(estado));

        List<EstadoPrenda> estados = estadoPrendaService.findAll();
        assertNotNull(estados);
        assertEquals(1, estados.size());
    }

    // Buscar un estado por ID
    @Test
    public void testFindById() {
        Integer id = 1;
        EstadoPrenda estado = new EstadoPrenda(id, LocalDateTime.now(), "En tránsito");
        when(estadoPrendaRepositorio.findById(id.longValue())).thenReturn(Optional.of(estado));

        EstadoPrenda found = estadoPrendaService.findEstadoById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_estado_prenda());
    }

    // Guardar un estado
    @Test
    public void testCreateEstado() {
        EstadoPrenda estado = new EstadoPrenda(1, LocalDateTime.now(), "En bodega");
        when(estadoPrendaRepositorio.save(estado)).thenReturn(estado);

        EstadoPrenda saved = estadoPrendaService.createEstado(estado);
        assertNotNull(saved);
        assertEquals("En bodega", saved.getEstado());
    }

    // Eliminar un estado por ID
    @Test
    public void testDeleteById() {
        Integer id = 1;
        when(estadoPrendaRepositorio.existsById(id.longValue())).thenReturn(true);
        doNothing().when(estadoPrendaRepositorio).deleteById(id.longValue());

        boolean deleted = estadoPrendaService.deleteById(id);
        assertTrue(deleted);
        verify(estadoPrendaRepositorio, times(1)).deleteById(id.longValue());
    }

    // Actualizar un estado existente
    @Test
    public void testUpdateEstado() {
        Integer id = 1;
        EstadoPrenda estadoNuevo = new EstadoPrenda(null, LocalDateTime.now().plusHours(1), "Procesado");

        when(estadoPrendaRepositorio.existsById(id.longValue())).thenReturn(true);
        when(estadoPrendaRepositorio.save(any(EstadoPrenda.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EstadoPrenda actualizado = estadoPrendaService.updateEstado(id, estadoNuevo);

        assertNotNull(actualizado);
        assertEquals("Procesado", actualizado.getEstado());
        assertEquals(id, actualizado.getId_estado_prenda());
    }
}
