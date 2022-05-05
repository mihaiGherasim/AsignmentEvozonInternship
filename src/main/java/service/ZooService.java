package service;

import model.Animal;
import repository.ZooRepository;

import java.util.List;

public class ZooService {
    private ZooRepository repository;

    public ZooService(ZooRepository repository){
        this.repository = repository;
    }

    public boolean deleteAnimal(String animal) {
        return repository.deleteAnimal(animal);
    }

    public boolean updateNameForAnAnimal(String animal, String name) {
        return repository.updateNameForAnAnimal(animal, name);
    }

    public String printNameForAnAnimal(String animal) {
         return repository.printNameForAnAnimal(animal);
    }

    public boolean addNameForAnAnimal(String animal, String name) {
        return repository.addNameForAnimal(animal, name);
    }

    public boolean addAnimal(String animal, String name) {
        return repository.addAnimal(animal, name);
    }

    public List<Animal> getAllAnimals() {
        return repository.getAllAnimals();
    }
}
