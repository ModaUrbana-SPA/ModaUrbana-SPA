package com.example.cl.com.ModaUrbanaSPA.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.cl.com.ModaUrbanaSPA.model.Prenda;
import com.example.cl.com.ModaUrbanaSPA.repository.PrendaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")

public class PrendaServiceTest {
    @Autowired
    private PrendaService prendaService;

    @MockBean
    private PrendaRepository prendaRepositorio;

    // Listar todas las prendas
    @Test
    public void testFindAll() {
        when(prendaRepositorio.findAll()).thenReturn(List.of(new Prenda(1, "Camisa formal caballero", 25000, "L", 10,
                "Negro", "MONO_HOODIE_ESTAMPADO.PNG", null, null)));

        List<Prenda> prendas = prendaService.findAll();
        assertNotNull(prendas);
        assertEquals(1, prendas.size());
    }

    // Buscar una prenda por ID
    @Test
    public void testFindById() {
        Integer id = 1;
        Prenda prenda = new Prenda(id, "Camisa formal caballero", 25000, "L", 10, "Negro", "MONO_HOODIE_ESTAMPADO.PNG",
                null, null);
        when(prendaRepositorio.findById(id)).thenReturn(Optional.of(prenda));

        Prenda found = prendaService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_prenda());
    }

    // Guardar una prenda
    @Test
    public void testSave() {
        Prenda prenda = new Prenda(1, "Camisa formal caballero", 25000, "L", 10, "Negro", "MONO_HOODIE_ESTAMPADO.PNG",
                null, null);
        when(prendaRepositorio.save(prenda)).thenReturn(prenda);

        Prenda saved = prendaService.save(prenda);
        assertNotNull(saved);
        assertEquals("Camisa formal caballero", saved.getNombre_prenda());
    }

    // Eliminar una prenda por ID
    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(prendaRepositorio).deleteById(id);

        prendaService.deleteById(id);
        verify(prendaRepositorio, times(1)).deleteById(id);
    }
}
