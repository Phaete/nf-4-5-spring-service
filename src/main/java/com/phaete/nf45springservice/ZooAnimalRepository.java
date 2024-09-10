package com.phaete.nf45springservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ZooAnimalRepository extends MongoRepository<ZooAnimal, String> {
}
