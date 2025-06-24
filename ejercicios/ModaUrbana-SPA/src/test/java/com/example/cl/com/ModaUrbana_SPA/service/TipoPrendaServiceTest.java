package com.example.cl.com.ModaUrbana_SPA.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.cl.com.ModaUrbanaSPA.model.TipoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.TipoPrendaRepositoriy;
import com.example.cl.com.ModaUrbanaSPA.service.TipoPrendaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TipoPrendaServiceTest {

    @Autowired
    private TipoPrendaService tipoPrendaService;

    @MockBean
    private TipoPrendaRepositoriy tipoPrendaRepository;

    @Test
    public void testFindAll() {
        when(tipoPrendaRepository.findAll()).thenReturn(List.of(new TipoPrenda(1, "Camisas", "Tipos de camisas formales y casuales")));

        List<TipoPrenda> tipos = tipoPrendaService.findAll();
        assertNotNull(tipos);
        assertEquals(1, tipos.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        TipoPrenda tipoPrenda = new TipoPrenda(id, "Camisas", "Tipos de camisas formales y casuales");
        when(tipoPrendaRepository.findById(id)).thenReturn(Optional.of(tipoPrenda));

        TipoPrenda found = tipoPrendaService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_tipo_prenda());
    }

    @Test
    public void testSave() {
        TipoPrenda tipoPrenda = new TipoPrenda(1, "Camisas", "Tipos de camisas formales y casuales");
        when(tipoPrendaRepository.save(tipoPrenda)).thenReturn(tipoPrenda);

        TipoPrenda saved = tipoPrendaService.save(tipoPrenda);
        assertNotNull(saved);
        assertEquals("Camisas", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(tipoPrendaRepository).deleteById(id);

        tipoPrendaService.deleteById(id);
        verify(tipoPrendaRepository, times(1)).deleteById(id);
    }
}
