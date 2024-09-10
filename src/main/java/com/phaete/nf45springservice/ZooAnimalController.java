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
    public List<DTOZooAnimal> getAnimals(){
        return zooAnimalService.getAnimals().stream()
                .map(zooAnimal -> new DTOZooAnimal(zooAnimal.id(), zooAnimal.name(), zooAnimal.age()))
                .toList();
    }

    @PostMapping()
    public DTOZooAnimal createAnimal(@RequestBody ZooAnimal zooAnimal){
        ZooAnimal createdZooAnimal = zooAnimalService.createAnimal(zooAnimal);
        return new DTOZooAnimal(createdZooAnimal.id(), createdZooAnimal.name(), createdZooAnimal.age());
    }
}
