package com.company;

public class Pheasant extends Animal {

    public Pheasant(String name, String gender)
    {
        super(name, gender);
        price = 20;
        numberOfBabies = 3;
    }

    public boolean eat(Food food, int kilos){
        if(food instanceof LivingFlies || food instanceof CatChow){
            System.out.printf("%s turns it's head away in disgust.\nPheasants only eat Sweet Corn!", this.name);
            return false;
        }
        super.eat(food, kilos);
        return true;
    }

}
