package repository;

import model.Animal;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZooRepository {
    private String fileName;
    private List<Animal> animals;
    private File file;
    private final String firstRow = "Animal,Name\n";

    public ZooRepository(String fileName){
        this.fileName = fileName;
        animals = new ArrayList<>();
        initialize();
    }

    /**
     * read all animals from the file
     * @throws FileNotFoundException
     */
    private void readFromFile() throws FileNotFoundException {
        animals.clear();
        Scanner reader = new Scanner(file);
        reader.nextLine();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] animalAndNameFromFile = line.split(",");
            if(animalAndNameFromFile.length == 2) {
                animals.add(new Animal(animalAndNameFromFile[0], animalAndNameFromFile[1]));
            }
            else {
                animals.add(new Animal(animalAndNameFromFile[0], null));
            }
        }
        reader.close();
    }

    /**
     * file with name fileName and add some data
     * @throws IOException
     */
    private void createAndInsertData() throws IOException {
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(firstRow);
        fileWriter.write("bear,Baloo\n");
        fileWriter.write("penguin,Aplodor\n");
        fileWriter.close();
    }

    /**
     * create a file with name fileName if that file does not exist
     */
    private void initialize() {
        try {
            file = new File(fileName);
            if(file.exists()){
                readFromFile();
            }
            else{
                createAndInsertData();
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * delete an Animal by animal
     * @param animal
     * @return true if Animal removed successfully, false otherwise
     */
    public boolean deleteAnimal(String animal) {
        try {
            readFromFile();
            for(Animal animalObj:animals){
                if(animalObj.getAnimal().equals(animal)){
                    animals.remove(animalObj);
                    System.out.println(animals.size());
                    removeFromFile();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * write an Animal entity to file
     * @param animal
     */
    private void writeAnimalEntityToFile(Animal animal) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(animal.getAnimal()+","+animal.getName());
            writer.newLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * remove al data from file with name fileName
     */
    private void removeFromFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write("");
            writer.write(firstRow);
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        animals.forEach(e->writeAnimalEntityToFile(e));
    }

    /**
     * set name @name to an entity Animal identified by animal
     * @param animal
     * @param name
     * @return true if name updated successfully false otherwise
     */
    public boolean updateNameForAnAnimal(String animal, String name) {
        try {
            readFromFile();
            for(Animal animalObj:animals){
                if(animalObj.getAnimal().equals(animal)){
                    animalObj.setName(name);
                    removeFromFile();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param animal
     * @return name for an entity Animal identified by animal
     */
    public String printNameForAnAnimal(String animal) {
        try {
            readFromFile();
            for(Animal animalObj:animals){
                if(animalObj.getAnimal().equals(animal)){
                    return animalObj.getName();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * set name for an entity Animal identified by animal if this Animal does not have a name
     * @param animal
     * @param name
     * @return true if name added successfully false otherwise
     */
    public boolean addNameForAnimal(String animal, String name) {
        try {
            readFromFile();
            for(Animal animalObj:animals){
                if(animalObj.getAnimal().equals(animal) && animalObj.getName() == null) {
                    animalObj.setName(name);
                    removeFromFile();
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * add an entity Animal to file
     * @param animal
     * @param name
     * @return true if Animal added successfully, false otherwise
     */
    public boolean addAnimal(String animal, String name) {
        try {
            readFromFile();
            for(Animal animal1:animals){
                if(animal1.getAnimal().equals(animal)){
                    return false;
                }
            }
            if(name != null) {
                animals.add(new Animal(animal, name));
            }
            else{
                Animal animal1 = new Animal();
                animal1.setAnimal(animal);
                animals.add(animal1);
            }
            removeFromFile();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @return all Animals from file
     */
    public List<Animal> getAllAnimals() {
        try {
            readFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return animals;
    }
}
