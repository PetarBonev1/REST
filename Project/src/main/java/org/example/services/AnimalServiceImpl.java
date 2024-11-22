package org.example.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Animal;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final List<Animal> animalList = new ArrayList<>();

    private static final String FILE_PATH = "animals.json";

    @Override
    public Animal addAnimal(Animal animal) {
        animalList.add(animal);
        //saveToFile();
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

    //private void saveToFile() {
    //    ObjectMapper mapper = new ObjectMapper();
    //    try {
    //        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), animalList);
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //        throw new RuntimeException("Failed to save animals to file", e);
    //    }
    //}
}
