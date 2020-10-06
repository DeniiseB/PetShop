package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private String name;
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
        Random random = new Random();
        String sentence = "";
        ArrayList<Animal> toRemove = new ArrayList<>();

        for(Animal animal: this.animals){
            int i = ThreadLocalRandom.current().nextInt(10, 30 + 1);
            animal.health = animal.health - i;
            if(animal.health <= 0){
                sentence = sentence + animal.getClass().getSimpleName() + ", " + animal.name + ", " + animal.getGender()
                        + ", " + "100% DEAD";
                toRemove.add(animal);
                continue;
            }
            sentence = sentence + animal.getClass().getSimpleName() + ", " + animal.name + ", "
                    + animal.getGender() + ", " + animal.health + "% Health";
        }
        // to avoid ConcurrentModificationException
        this.animals.removeAll(toRemove);
        return sentence;
    }

    public String foodInfo(){
        return "Living Flies: " + this.livingFlies.getKilos() + "Kg  |  " +
                "Sweet Corn: " + this.sweetCorn.getKilos() + "Kg  |  " +
                "Cat Chow: " + this.catChow.getKilos() + "Kg";
    }

}
