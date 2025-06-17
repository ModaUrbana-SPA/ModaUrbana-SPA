package com.example.cl.com.ModaUrbana_SPA.service;

import com.example.cl.com.ModaUrbanaSPA.service.TipoPrendaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.TipoPrendaRepositorio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TipoPrendaServiceTest {

    @Mock
    private TipoPrendaRepositorio tipoPrendaRepositorio;

    @InjectMocks
    private TipoPrendaService tipoPrendaService;

    private TipoPrenda tipoPrenda;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        tipoPrenda = new TipoPrenda();
        tipoPrenda.setId_tipo_prenda(1);
        tipoPrenda.setId(1L);
        tipoPrenda.setNombre("Camisas");
        tipoPrenda.setDescripcion("Tipos de camisas formales y casuales");
    }

    @Test
    public void testFetchAll() {
        when(tipoPrendaRepositorio.findAll()).thenReturn(List.of(tipoPrenda));

        List<TipoPrenda> resultado = tipoPrendaService.fetchAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Camisas", resultado.get(0).getNombre());

        verify(tipoPrendaRepositorio, times(1)).findAll();
    }

    @Test
    public void testFetchById_Existente() {
        when(tipoPrendaRepositorio.findById(1L)).thenReturn(Optional.of(tipoPrenda));

        TipoPrenda resultado = tipoPrendaService.fetchById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Camisas", resultado.getNombre());

        verify(tipoPrendaRepositorio, times(1)).findById(1L);
    }

    @Test
    public void testFetchById_NoExistente() {
        when(tipoPrendaRepositorio.findById(2L)).thenReturn(Optional.empty());

        TipoPrenda resultado = tipoPrendaService.fetchById(2L);

        assertNull(resultado);

        verify(tipoPrendaRepositorio, times(1)).findById(2L);
    }

    @Test
    public void testSave() {
        when(tipoPrendaRepositorio.save(any(TipoPrenda.class))).thenReturn(tipoPrenda);

        TipoPrenda resultado = tipoPrendaService.save(tipoPrenda);

        assertNotNull(resultado);
        assertEquals("Camisas", resultado.getNombre());

        verify(tipoPrendaRepositorio, times(1)).save(tipoPrenda);
    }

    @Test
    public void testDelete() {
        doNothing().when(tipoPrendaRepositorio).deleteById(1L);

        tipoPrendaService.delete(1L);

        verify(tipoPrendaRepositorio, times(1)).deleteById(1L);
    }
}
