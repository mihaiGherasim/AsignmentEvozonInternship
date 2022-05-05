package service;

import model.Animal;
import repository.ZooRepository;

import java.util.List;

public class ZooService {
    private ZooRepository repository;

    public ZooService(ZooRepository repository){
        this.repository = repository;
    }

    /**
     * delete an Animal by animal
     * @param animal
     * @return true if Animal removed successfully, false otherwise
     */
    public boolean deleteAnimal(String animal) {
        return repository.deleteAnimal(animal);
    }

    /**
     * set name @name to an entity Animal identified by animal
     * @param animal
     * @param name
     * @return true if name updated successfully false otherwise
     */
    public boolean updateNameForAnAnimal(String animal, String name) {
        return repository.updateNameForAnAnimal(animal, name);
    }

    /**
     * @param animal
     * @return name for an entity Animal identified by animal
     */
    public String printNameForAnAnimal(String animal) {
         return repository.printNameForAnAnimal(animal);
    }

    /**
     * set name for an entity Animal identified by animal if this Animal does not have a name
     * @param animal
     * @param name
     * @return true if name added successfully false otherwise
     */
    public boolean addNameForAnAnimal(String animal, String name) {
        return repository.addNameForAnimal(animal, name);
    }

    /**
     * add an entity Animal to file
     * @param animal
     * @param name
     * @return true if Animal added successfully, false otherwise
     */
    public boolean addAnimal(String animal, String name) {
        return repository.addAnimal(animal, name);
    }

    /**
     *
     * @return all Animals from file
     */
    public List<Animal> getAllAnimals() {
        return repository.getAllAnimals();
    }
}
