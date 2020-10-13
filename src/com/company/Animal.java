package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

abstract class Animal implements Serializable {

    Random random = new Random();

    protected String name;
    protected int health = 100;
    protected String gender;
    protected int price;
    protected int numberOfBabies;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = setGender(gender);
    }

    private String setGender(String i){
        return ((i.contains("f") || i.contains("F")) ? "Female" : "Male");
    }

    public String getRandomGender(){
        int i = random.nextInt(2)+1;
        return (i == 1 ? "Female" : "Male");
    }

    public String getGender(){
        return gender;
    }

    public int getPrice(){
        return price;
    }

    public boolean eat(Food food, int kilos){
        this.health += (kilos * 10);
        if(this.health > 100) {
            this.health = 100;
        }
        System.out.println(this.name + ": Omnomnom..." + "\n" + "^^ " + this.health + "% health");
        try {
            Thread.sleep(3000);
        }
        catch(Exception ignore){}

        return true;
    }

    public Animal createBabyAnimal(Animal partner, String name, String gender){
        String className = this.getClass().getSimpleName();
        Animal babyAnimal = switch (className){
            case "Toad" -> new Toad(name, gender);
            case "Pike" -> new Pike(name, gender);
            case "Pheasant" -> new Pheasant(name, gender);
            case "Ferret" -> new Ferret(name, gender);
            default -> new Badger(name,gender);
        };
        return babyAnimal;
    }


    public int worth(){
       double h = this.health;
       // Rounds the double up or down to nearest int
       int toReturn = (int)Math.round((h/100) * this.price);
       return toReturn;
    }

}
