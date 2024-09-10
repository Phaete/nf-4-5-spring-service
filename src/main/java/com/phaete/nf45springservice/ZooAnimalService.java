package com.phaete.nf45springservice;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ZooAnimalService {

    private final ZooAnimalRepository zooAnimalRepository;

    public ZooAnimalService(ZooAnimalRepository zooAnimalRepository) {
        this.zooAnimalRepository = zooAnimalRepository;
    }

    public ZooAnimal createAnimal(ZooAnimal zooAnimal) {
        return zooAnimalRepository.save(
                new ZooAnimal(UUID.randomUUID().toString(), zooAnimal.name(), zooAnimal.age(), new BigDecimal("200.00"))
        );
    }

    public List<ZooAnimal> getAnimals() {
        return zooAnimalRepository.findAll();
    }
}
