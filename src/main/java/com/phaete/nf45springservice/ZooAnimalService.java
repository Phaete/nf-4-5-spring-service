package com.phaete.nf45springservice;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ZooAnimalService {

    private final ZooAnimalRepository zooAnimalRepository;

    public ZooAnimalService(ZooAnimalRepository zooAnimalRepository) {
        this.zooAnimalRepository = zooAnimalRepository;
    }

    public List<ZooAnimal> findAllAnimals() {
        return zooAnimalRepository.findAll();
    }

    public ZooAnimal findAnimalById(String id) {
        return zooAnimalRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public ZooAnimal createAnimal(DTOZooAnimal DTOZooAnimal) {
        return zooAnimalRepository.save(
                new ZooAnimal(UUID.randomUUID().toString(), DTOZooAnimal.name(), DTOZooAnimal.age(), new BigDecimal("200.00"))
        );
    }

    public String deleteAnimal(String id) {
        try {
            zooAnimalRepository.delete(findAnimalById(id));
            return "Animal deleted";
        } catch (NoSuchElementException e) {
            return e.getMessage();
        }
    }

}
