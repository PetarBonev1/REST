package org.example;

import org.example.models.Animal;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController


public class Controller {
    ArrayList<Animal> AnimalList = new ArrayList<Animal>();

    @GetMapping("/public")
    public String ServText() {
        return "test";
    }

    @GetMapping(value = "/animal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Animal> GetAnimall() {


        Animal animal = new Animal("pesho", 15);
        return ResponseEntity.ok(animal);
    }
    @PostMapping("/animal")
    public ResponseEntity postController(
            @RequestBody Animal animal) {

        AnimalList.add(animal);
        return ResponseEntity.ok(animal);
    }
    @GetMapping(value = "/animallist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Animal>> PrintAnimal() {

        return ResponseEntity.ok(this.AnimalList);
    }

    @GetMapping(value = "/animallistold", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Animal>> PrintOldAnimals() {
        ArrayList<Animal> filteredAnimals = new ArrayList<>();

        for (Animal animal : AnimalList) {
            if (animal.getAge() > 15) {
                filteredAnimals.add(animal);
            }
        }
        return ResponseEntity.ok(filteredAnimals);
    }

}
