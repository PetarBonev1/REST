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
        saveToFile(); // Ensure changes are saved to file
        return animal;
    }

    @Override
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animalList); // Return a copy to avoid modifying the internal list
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
                saveToFile(); // Save updated data to file
                return animal;
            }
        }
        throw new IllegalArgumentException("Animal not found");
    }

    @Override
    public boolean deleteAnimal(String name) {
        boolean removed = animalList.removeIf(animal -> animal.getName().equals(name));
        if (removed) {
            saveToFile(); // Save changes to file after deletion
        }
        return removed;
    }

    /**
     * Saves the current state of animalList to a JSON file.
     */
    private void saveToFile() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_PATH);

        try {
            System.out.println("Saving to file: " + file.getAbsolutePath());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, animalList);
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
