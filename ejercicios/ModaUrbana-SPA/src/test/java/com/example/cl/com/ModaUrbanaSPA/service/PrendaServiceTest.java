package com.example.cl.com.ModaUrbanaSPA.service;

import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.repository.PrendaRepositorio;
import com.example.cl.com.ModaUrbanaSPA.service.PrendaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class PrendaServiceTest {

    @Autowired
    private PrendaService prendaService;

    @MockBean
    private PrendaRepositorio prendaRepositorio;

    @Test
    public void testFindAll() {
        Prenda prenda = crearPrenda();
        when(prendaRepositorio.findAll()).thenReturn(List.of(prenda));

        List<Prenda> prendas = prendaService.findAll();
        assertNotNull(prendas);
        assertEquals(1, prendas.size());
        assertEquals(prenda.getId_prenda(), prendas.get(0).getId_prenda());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Prenda prenda = crearPrenda();
        when(prendaRepositorio.findById(id)).thenReturn(Optional.of(prenda));

        Prenda found = prendaService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_prenda());
    }

    @Test
    public void testSave() {
        Prenda prenda = crearPrenda();
        when(prendaRepositorio.save(prenda)).thenReturn(prenda);

        Prenda saved = prendaService.save(prenda);
        assertNotNull(saved);
        assertEquals("Camisa formal caballero", saved.getNombre_prenda());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(prendaRepositorio).deleteById(id);

        prendaService.deleteById(id);
        verify(prendaRepositorio, times(1)).deleteById(id);
    }

    private Prenda crearPrenda() {
        Prenda prenda = new Prenda();
        prenda.setId_prenda(1);
        prenda.setNombre_prenda("Camisa formal caballero");
        prenda.setPrecio(25000);
        prenda.setImagen("MONO_HOODIE_ESTAMPADO.PNG");
        prenda.setColor("Negro");
        prenda.setTalla("L");
        return prenda;
    }
}
