package org.example.services;

import org.example.models.Animal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final List<Animal> animalList = new ArrayList<>();

    @Override
    public Animal addAnimal(Animal animal) {
        animalList.add(animal);
        return animal;
    }

    @Override
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animalList);
    }

    @Override
    public List<Animal> getAnimalsOlderThan(int age) {
        List<Animal> filteredAnimals = new ArrayList<>();
        for (Animal animal : animalList) {
            if (animal.getAge() > age) {
                filteredAnimals.add(animal);
            }
        }
        return filteredAnimals;
    }

    @Override
    public Animal updateAnimalName(String currentName, String newName) {
        for (Animal animal : animalList) {
            if (animal.getName().equals(currentName)) {
                animal.setName(newName);
                return animal;
            }
        }
        throw new IllegalArgumentException("Animal not found");
    }

    @Override
    public boolean deleteAnimal(String name) {
        return animalList.removeIf(animal -> animal.getName().equals(name));
    }
}
