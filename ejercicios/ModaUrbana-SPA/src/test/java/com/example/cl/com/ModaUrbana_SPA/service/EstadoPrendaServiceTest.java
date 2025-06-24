package com.example.cl.com.ModaUrbana_SPA.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.cl.com.ModaUrbanaSPA.model.EstadoPrenda;
import com.example.cl.com.ModaUrbanaSPA.repository.EstadoPrendaRepositoriy;
import com.example.cl.com.ModaUrbanaSPA.service.EstadoPrendaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EstadoPrendaServiceTest {

    @Autowired
    private EstadoPrendaService estadoPrendaService;

    @MockBean
    private EstadoPrendaRepositoriy estadoPrendaRepository;

    // Listar todos los estados
    @Test
    public void testFindAll() {
        when(estadoPrendaRepository.findAll()).thenReturn(List.of(new EstadoPrenda(1, "Disponible")));

        List<EstadoPrenda> estados = estadoPrendaService.findAll();
        assertNotNull(estados);
        assertEquals(1, estados.size());
    }

    // Buscar un estado por ID
    @Test
    public void testFindById() {
        Integer id = 1;
        EstadoPrenda estado = new EstadoPrenda(id, "En tránsito");
        when(estadoPrendaRepository.findById(id)).thenReturn(Optional.of(estado));

        EstadoPrenda found = estadoPrendaService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_estado_prenda());
    }

    // Guardar un estado
    @Test
    public void testSave() {
        EstadoPrenda estado = new EstadoPrenda(1, "En bodega");
        when(estadoPrendaRepository.save(estado)).thenReturn(estado);

        EstadoPrenda saved = estadoPrendaService.save(estado);
        assertNotNull(saved);
        assertEquals("En bodega", saved.getEstado());
    }

    // Eliminar un estado por ID
    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(estadoPrendaRepository).deleteById(id);

        estadoPrendaService.deleteById(id);
        verify(estadoPrendaRepository, times(1)).deleteById(id);
    }

}
