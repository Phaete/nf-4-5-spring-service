package com.phaete.nf45springservice;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animals")
public class ZooAnimalController {

    private final ZooAnimalService zooAnimalService;

    public ZooAnimalController(ZooAnimalService zooAnimalService) {
        this.zooAnimalService = zooAnimalService;
    }

    @GetMapping()
    public List<DTOZooAnimal> findAllAnimals(){
        return zooAnimalService.findAllAnimals().stream()
                .map(zooAnimal -> new DTOZooAnimal(zooAnimal.id(), zooAnimal.name(), zooAnimal.age()))
                .toList();
    }

    @PostMapping()
    public ZooAnimal createAnimal(@RequestBody ZooAnimal zooAnimal){
        return zooAnimalService.createAnimal(new DTOZooAnimal(zooAnimal.id(), zooAnimal.name(), zooAnimal.age()));
    }
}
