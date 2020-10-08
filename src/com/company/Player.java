package com.company;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private final String name;
    private int money = 100;
    public ArrayList<Animal> animals;
    public LivingFlies livingFlies = new LivingFlies();
    public SweetCorn sweetCorn =  new SweetCorn();
    public CatChow catChow = new CatChow();


    public Player(String name){

        this.name = name;
        this.animals = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int newMoney){
        this.money = newMoney;
    }

    public String animalsInfoReduceHealth(){
        String sentence = "";
        ArrayList<Animal> toRemove = new ArrayList<>();

        for(Animal animal: this.animals){
            int i = ThreadLocalRandom.current().nextInt(10, 30 + 1);
            animal.health = animal.health - i;
            if(animal.health <= 0){
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

    public String animalsInfo(){
        String sentence = "";
        int h = 0;
        for(Animal animal: this.animals){
            h++;
           sentence += "\n" + h + ". " + animal.getClass().getSimpleName() + ", " + animal.name + ", "
                    + animal.getGender() + ", " + animal.health + "% Health";
        }
        return sentence;
    }

    public String foodInfo(){
        return "1. Living Flies: " + this.livingFlies.getKilos() + "Kg  |  " +
                "2. Sweet Corn: " + this.sweetCorn.getKilos() + "Kg  |  " +
                "3. Cat Chow: " + this.catChow.getKilos() + "Kg";
    }

    public int foodTotalKilos()
    {
        return this.livingFlies.getKilos() + this.sweetCorn.getKilos() + this.catChow.getKilos();
    }

    public boolean feed(Food food){
        if(food == null || food.kilos == 0){
            print("You don't have that type of food.");
            return false;}
        if(this.animals.size() == 0) {
            print("You don't have any pets to feed!");
            return false;
        }
        int kilos = Dialogs.promptInt("How many kilos? (Each kilo gives 10% health)", 0, 10000);

        if(kilos > food.kilos){
            print("You don't have that much food.");
            return false;
        }

        System.out.println(this.animalsInfo());
        int choice = Dialogs.promptInt("--Pick pet(number) to feed--", 0,this.animals.size());

        Animal animal = this.animals.get(choice - 1);

        return animal.eat(food, kilos);

    }

    private void print(String x) {
        // print a string if it is not empty
        if (!x.equals("")) {
            System.out.println(x);
        }
    }

}
