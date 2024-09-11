package com.phaete.nf45springservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ZooAnimalControllerIntegrationsTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ZooAnimalRepository zooAnimalRepository;

    @Test
    void testFindAllAnimals_returnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    []
                    """));
    }

    @Test
    @DirtiesContext
    void testFindAllAnimals_returnsListOfSavedAnimals() throws Exception {
        zooAnimalRepository.save(new ZooAnimal("111", "Lion", 5, new BigDecimal("200.00")));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{"id":"111","name":"Lion","age":5}]
                    """));
    }

    @Test
    void testCreateAnimal_returnsStatusOk_WithSavedAnimal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name":"Lion",
                        "age":5
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {"name":"Lion","age":5}
                    """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

}
