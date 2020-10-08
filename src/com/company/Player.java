package com.company;

import java.util.ArrayList;
import java.util.Random;
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

    public String animalsInfo(){
        String sentence = "";
        ArrayList<Animal> toRemove = new ArrayList<>();
        int h = 0;

        for(Animal animal: this.animals){
            h++;
            int i = ThreadLocalRandom.current().nextInt(10, 30 + 1);
            animal.health = animal.health - i;
            if(animal.health <= 0){
                sentence = sentence + "\n" + h + ". " + animal.getClass().getSimpleName() + ", " + animal.name + ", " + animal.getGender()
                        + ", " + "100% DEAD";
                toRemove.add(animal);
                continue;
            }
            sentence = sentence + "\n" + h + ". " + animal.getClass().getSimpleName() + ", " + animal.name + ", "
                    + animal.getGender() + ", " + animal.health + "% Health";
        }
        // to avoid ConcurrentModificationException
        this.animals.removeAll(toRemove);
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

    public void feed(Food food){
        if(this.animals.size() == 0) {
            System.out.println("You don't have any pets to feed!");
            return;
        }
        System.out.println(this.animalsInfo());
        int choice = Dialogs.promptInt("--Pick pet(number) to feed--\n", 0,this.animals.size());

        Animal animal = this.animals.get(choice - 1);

        int kilos = Dialogs.promptInt("Enter kilos to feed " + animal.name +
                ":", 0, 10000);
        animal.eat(food, kilos);
    }

}
