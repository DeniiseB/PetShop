package com.company;

import java.io.Serializable;

public class Pheasant extends Animal implements Serializable {

    public Pheasant(String name, String gender)
    {
        super(name, gender);
        price = 20;
        numberOfBabies = 3;
        maxAge = 12;
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
