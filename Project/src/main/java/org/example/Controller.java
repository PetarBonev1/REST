package org.example;

import org.example.models.Animal;
import org.example.services.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final AnimalService animalService;

    public Controller(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/public")
    public String serveText() {
        return "test";
    }

    @GetMapping(value = "/animal", produces = "application/json")
    public ResponseEntity<Animal> getAnimal() {
        Animal animal = new Animal("pesho", 15);
        return ResponseEntity.ok(animal);
    }

    @PostMapping("/animal")
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        Animal addedAnimal = animalService.addAnimal(animal);
        return ResponseEntity.ok(addedAnimal);
    }

    @GetMapping(value = "/animallist", produces = "application/json")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @GetMapping(value = "/animallistold", produces = "application/json")
    public ResponseEntity<List<Animal>> getAnimalsOlderThan() {
        return ResponseEntity.ok(animalService.getAnimalsOlderThan(15));
    }

    @PutMapping("/animalchangename/{currentName}")
    public ResponseEntity<Animal> updateAnimalName(@PathVariable String currentName, @RequestParam String newName) {
        try {
            Animal updatedAnimal = animalService.updateAnimalName(currentName, newName);
            return ResponseEntity.ok(updatedAnimal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/animaldelete/{currentName}")
    public ResponseEntity<String> deleteAnimal(@PathVariable String currentName) {
        boolean isDeleted = animalService.deleteAnimal(currentName);
        if (isDeleted) {
            return ResponseEntity.ok("Animal deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}
