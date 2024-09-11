package com.phaete.nf45springservice;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZooAnimalServiceTest {

    private final ZooAnimalRepository zooAnimalRepository= mock(ZooAnimalRepository.class);
    private final ZooAnimalService zooAnimalService = new ZooAnimalService(zooAnimalRepository);


    @Test
    void findAllAnimals() {
        ZooAnimal zooAnimal = new ZooAnimal("1", "Lion", 5, new BigDecimal("200.00"));
        when(zooAnimalRepository.findAll()).thenReturn(List.of(zooAnimal));
        List<ZooAnimal> actual = zooAnimalService.findAllAnimals();
        verify(zooAnimalRepository).findAll();
        assertEquals(List.of(zooAnimal), actual);
    }


    @Test
    void findAnimalById() {
        ZooAnimal expected = new ZooAnimal("1", "Lion", 5, new BigDecimal("200.00"));
        when(zooAnimalRepository.findById("1")).thenReturn(Optional.of(expected));
        ZooAnimal actual = zooAnimalService.findAnimalById("1");
        verify(zooAnimalRepository).findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void findAnimalById_ThrowsOnNotFound() {
        when(zooAnimalRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> zooAnimalService.findAnimalById("1"));
    }

    @Test
    void createAnimal() {
        ZooAnimal expected = new ZooAnimal("1", "Lion", 5, new BigDecimal("200.00"));
        when(zooAnimalRepository.save(any(ZooAnimal.class))).thenReturn(expected);
        assertEquals(expected, zooAnimalService.createAnimal(new DTOZooAnimal("1", "Lion", 5)));
        verify(zooAnimalRepository).save(any(ZooAnimal.class));
    }

    @Test
    void deleteAnimal() {
        ZooAnimal expected = new ZooAnimal("1", "Lion", 5, new BigDecimal("200.00"));
        when(zooAnimalRepository.findById("1")).thenReturn(Optional.of(expected));
        assertEquals("Animal deleted", zooAnimalService.deleteAnimal("1"));
        verify(zooAnimalRepository).delete(any(ZooAnimal.class));
    }
}