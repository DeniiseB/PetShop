package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements Serializable {

    Random random = new Random();
    private final String name;
    private int money = 130;
    public ArrayList<Animal> animals;
    public LivingFlies livingFlies = new LivingFlies();
    public SweetCorn sweetCorn = new SweetCorn();
    public CatChow catChow = new CatChow();


    public Player(String name) {

        this.name = name;
        this.animals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int newMoney) {
        this.money = newMoney;
    }

    //A method for the beginning of each round
    public String animalsInfoReduceHealth() {
        String sentence = "";
        ArrayList<Animal> toRemove = new ArrayList<>();

        for (Animal animal : this.animals) {
            int i = ThreadLocalRandom.current().nextInt(10, 30 + 1);
            animal.health = animal.health - i;
            if (animal.health <= 0 || animal.isSick) {
                sentence += "\n" + animal.getClass().getSimpleName() + ", " + animal.name + ", " + animal.getGender()
                        + ", " + "100% DEAD";
                toRemove.add(animal);
                continue;
            }
            sentence += "\n" + animal.getClass().getSimpleName() + ", " + animal.name + ", "
                    + animal.getGender() + ", " + animal.health + "% Health";
        }
        // to avoid ConcurrentModificationException
        this.animals.removeAll(toRemove);
        return sentence;
    }

    public String animalsInfo() {
        String sentence = "";
        int h = 0;
        for (Animal animal : this.animals) {
            h++;
            sentence += "\n" + h + ". " + animal.getClass().getSimpleName() + ", " + animal.name + ", "
                    + animal.getGender() + ", " + animal.health + "% Health";
        }
        return sentence;
    }

    public String foodInfo() {
        return "1. Living Flies: " + this.livingFlies.getKilos() + "Kg  |  " +
                "2. Sweet Corn: " + this.sweetCorn.getKilos() + "Kg  |  " +
                "3. Cat Chow: " + this.catChow.getKilos() + "Kg";
    }

    public int foodTotalKilos() {
        return this.livingFlies.getKilos() + this.sweetCorn.getKilos() + this.catChow.getKilos();
    }

    public boolean feed() {

        if (this.animals.size() == 0) {
            print("You don't have any pets to feed!");
            return false;
        }
        if (this.foodTotalKilos() == 0) {
            print("You have to buy some food first.");
            return false;
        }

        print(this.foodInfo());

        int choice = Dialogs.promptInt("Which type of food(1-3)? (4.BACK)", 1, 4);
        if (choice == 4) {
            return false;
        }

        Food foodType = switch (choice) {
            case 1 -> this.livingFlies;
            case 2 -> this.sweetCorn;
            default -> this.catChow;
        };

        if (foodType.kilos == 0) {
            print("You have to buy some of that food first.");
            return false;
        }

        System.out.println(this.animalsInfo());
        choice = Dialogs.promptInt("--Pick pet(number) to feed--", 0, this.animals.size());
        Animal animal = this.animals.get(choice - 1);

        int kilos = Dialogs.promptInt("How many kilos? (Each kilo gives 10% health)", 0, 10000);

        if (kilos > foodType.kilos) {
            print("You don't have that much food.");
            return false;
        }
        return animal.eat(foodType, kilos);

    }


    public boolean createNewAnimals() {
        if (this.animals.size() == 0) {
            print("You don't have any pets.");
            return false;
        }
        Dialogs.clear();
        System.out.println(this.animalsInfo());
        int choice = Dialogs.promptInt("--Pick pet(number) to create baby animal--" +
                "\n(0. BACK)", 0, this.animals.size());
        if(choice == 0){return false;}
        Animal animal1 = this.animals.get(choice - 1);
        if(animal1.isSick){
            print(animal1.name + " is too sick to try for baby " + animal1.getClass().getSimpleName());
            return false;
        }
        String className1 = animal1.getClass().getSimpleName();
        ArrayList<Animal> options = new ArrayList<>();
        int i = 0;

        // Loops through the players animals to find a suitable match
        for (Animal animal : this.animals) {
            String className = animal.getClass().getSimpleName();
            if (!className1.equals(className)) {
                continue;
            }
            if (animal.getGender().equals(animal1.getGender())) {
                continue;
            }
            options.add(animal);
            print(++i + ". " + animal.name + ", " + animal.getClass().getSimpleName() + ", " + animal.getGender());
        }
        if (options.size() == 0) {
            print("\nThere are no suitable partners for " + animal1.name + ".\nBuy an animal of opposite gender " +
                    "but same species to succeed.");
            return false;
        }
        choice = Dialogs.promptInt("--Pick a partner for " + animal1.name + "--" +
                "\n(0. BACK)", 0, this.animals.size());
        if(choice == 0){return false;}
        Animal animal2 = options.get(choice - 1);
        if(animal2.isSick){
            print(animal2.name + " is too sick to try for baby " + animal2.getClass().getSimpleName());
            return false;
        }
        i = random.nextInt(2) + 1;
        if (i == 1) {
            print("Sorry, no baby " + animal1.getClass().getSimpleName() + "lings today =(");
            try {
                Thread.sleep(3000);
            }
            catch(Exception ignore){}
            return true;
        }

        int j = 0;
        do{
            j++;
        String gender = animal1.getRandomGender();
        print("Congrats! You have a new " + gender + " " + animal1.getClass().getSimpleName());
        String newName = Dialogs.promptString("Name your new pet:");

        this.animals.add(animal1.createBabyAnimal(animal2, newName, gender));
        }while(j < animal1.numberOfBabies);

        return true;
    }

    public void sickAnimals(){
        for(Animal animal:this.animals){
            double number = Math.random();
            // If random number is 0.2 or less, animal gets sick (20% chance)
            animal.isSick = number <= 0.2;
            print(animal.isSick ? "\n" + animal.name + " is sick! Take them to the vet or they'll die!" : "");
        }
    }


    private void print(String x) {
        // print a string if it is not empty
        if (!x.equals("")) {
            System.out.println(x);
        }
    }

}
