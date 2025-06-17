package com.example.cl.com.ModaUrbana_SPA.service;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.EstadoPrendaRepositorio;
import com.example.cl.com.ModaUrbanaSPA.service.EstadoPrendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EstadoPrendaServiceTest {

    @Mock
    private EstadoPrendaRepositorio estadoPrendaRepositorio;

    @InjectMocks
    private EstadoPrendaService estadoPrendaService;

    private EstadoPrenda estadoPrenda;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estadoPrenda = new EstadoPrenda(1, LocalDateTime.now(), "Disponible");
    }

    @Test
    void testFindAll() {
        when(estadoPrendaRepositorio.findAll()).thenReturn(Arrays.asList(estadoPrenda));
        List<EstadoPrenda> result = estadoPrendaService.findAll();
        assertEquals(1, result.size());
        verify(estadoPrendaRepositorio, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        when(estadoPrendaRepositorio.findById(1L)).thenReturn(Optional.of(estadoPrenda));
        EstadoPrenda result = estadoPrendaService.findEstadoById(1);
        assertNotNull(result);
        assertEquals("Disponible", result.getEstado());
    }

    @Test
    void testFindByIdNotFound() {
        when(estadoPrendaRepositorio.findById(1L)).thenReturn(Optional.empty());
        EstadoPrenda result = estadoPrendaService.findEstadoById(1);
        assertNull(result);
    }

    @Test
    void testCreateEstado() {
        when(estadoPrendaRepositorio.save(estadoPrenda)).thenReturn(estadoPrenda);
        EstadoPrenda result = estadoPrendaService.createEstado(estadoPrenda);
        assertEquals("Disponible", result.getEstado());
    }

    @Test
    void testUpdateEstadoExists() {
        when(estadoPrendaRepositorio.existsById(1L)).thenReturn(true);
        when(estadoPrendaRepositorio.save(estadoPrenda)).thenReturn(estadoPrenda);
        EstadoPrenda result = estadoPrendaService.updateEstado(1, estadoPrenda);
        assertNotNull(result);
    }

    @Test
    void testUpdateEstadoNotExists() {
        when(estadoPrendaRepositorio.existsById(1L)).thenReturn(false);
        EstadoPrenda result = estadoPrendaService.updateEstado(1, estadoPrenda);
        assertNull(result);
    }

    @Test
    void testDeleteEstadoExists() {
        when(estadoPrendaRepositorio.existsById(1L)).thenReturn(true);
        boolean deleted = estadoPrendaService.deleteEstado(1);
        assertTrue(deleted);
        verify(estadoPrendaRepositorio, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEstadoNotExists() {
        when(estadoPrendaRepositorio.existsById(1L)).thenReturn(false);
        boolean deleted = estadoPrendaService.deleteEstado(1);
        assertFalse(deleted);
    }
}
