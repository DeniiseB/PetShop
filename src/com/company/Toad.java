package com.company;

public class Toad extends Animal {

    public Toad(String name, String gender)
    {
        super(name, gender);
        price = 5;

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
