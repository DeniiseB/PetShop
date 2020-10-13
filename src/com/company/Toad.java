package com.company;

import java.io.Serializable;

public class Toad extends Animal implements Serializable {

    public Toad(String name, String gender)
    {
        super(name, gender);
        price = 5;
        numberOfBabies = 4;
    }

    public boolean eat(Food food, int kilos){
        if(food instanceof SweetCorn || food instanceof CatChow){
            System.out.printf("%s turns it's head away in disgust.\nToads only eat Living Flies!", this.name);
            return false;
        }
        super.eat(food, kilos);
        return true;
    }


}
