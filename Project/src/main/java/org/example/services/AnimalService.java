package org.example.services;

import org.example.models.Animal;

import java.util.List;

public interface AnimalService {
    Animal addAnimal(Animal animal);
    List<Animal> getAllAnimals();
    List<Animal> getAnimalsOlderThan(int age);
    Animal updateAnimalName(String currentName, String newName);
    boolean deleteAnimal(String name);
}
