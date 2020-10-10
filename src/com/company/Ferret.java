package com.company;

public class Ferret extends Animal {

    public Ferret(String name, String gender)
    {
        super(name, gender);
        price = 40;
        numberOfBabies = 2;
    }

    public boolean eat(Food food, int kilos){
        if(food instanceof LivingFlies){
            System.out.printf("%s turns it's head away in disgust.\nFerrets only eat Sweet Corn and Cat Chow!", this.name);
            return false;
        }
        super.eat(food, kilos);
        return true;
    }


}
