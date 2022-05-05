import model.Animal;
import repository.ZooRepository;
import service.ZooService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static String fileName = "data/zooCatalogue.csv";
    static private ZooRepository repo = new ZooRepository(fileName);
    static private ZooService service = new ZooService(repo);

    public static void main(String[] args){
        while(true){
            System.out.println("Choose your action:\n1.Add animal\n2.Add name to a specific animal\n3.Print name to a specific animal\n" +
                    "4.Update name of a specific animal\n5.Delete animal\n6.Print all animals\n0.Exit");
            System.out.print(">>>");
            Scanner scanner = new Scanner(System.in);
            try{
                int input = scanner.nextInt();
                if(input == 0){
                    System.out.println("You closed application!");
                    break;
                }
                switch (input){
                    case 1 -> addAnimal();
                    case 2 -> addNameForAnAnimal();
                    case 3 -> printNameForAnAnimal();
                    case 4 -> updateNameForAnAnimal();
                    case 5 -> deleteAnimal();
                    case 6 -> printAllAnimals();
                    default -> System.out.println("Invalid option!!!");
                }
            }catch (InputMismatchException e){
                System.out.println("Please insert an integer!");
            }
        }
    }

    private static void printAllAnimals() {
        for(Animal animal : service.getAllAnimals()){
            System.out.println(animal);
        }
    }

    private static void deleteAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter animal: ");
        String animal = scanner.next();
        if(service.deleteAnimal(animal)){
            System.out.println("Animal removed successfully!");
        }
        else{
            System.out.println("Cannot remove Animal!");
        }
    }

    private static void updateNameForAnAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter animal: ");
        String animal = scanner.next();
        System.out.print("Enter new name for this animal: ");
        String name = scanner.next();
        if(service.updateNameForAnAnimal(animal, name)){
            System.out.println("Name updated successfully!");
        }
        else{
            System.out.println("Cannot update name for this animal!!!");
        }
    }

    private static void printNameForAnAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter animal: ");
        String animal = scanner.next();
        String name = service.printNameForAnAnimal(animal);
        if(name != null) {
            System.out.println("Name for this animal is: " + service.printNameForAnAnimal(animal));
        }
        else{
            System.out.println("This animal does not have a name or this animal does not exist!!!");
        }
    }

    private static void addNameForAnAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter animal: ");
        String animal = scanner.next();
        System.out.print("Enter name to be added for this animal: ");
        String name = scanner.next();
        if(service.addNameForAnAnimal(animal, name)){
            System.out.println("Name added successfully for this animal!");
        }
        else {
            System.out.println("Cannot add name for this animal!");
        }
    }

    private static void addAnimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter animal: ");
        String animal = scanner.next();
        System.out.print("Enter name: ");
        String name = scanner.next();
        if(service.addAnimal(animal, name)){
            System.out.println("Animal added successfully!");
        }
        else{
            System.out.println("Cannot add this animal or already exist!");
        }
    }
}
