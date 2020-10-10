package com.company;

public class Badger extends Animal {

    public Badger(String name, String gender) {
        super(name, gender);
        price = 60;
        numberOfBabies = 2;
    }

    public boolean eat(Food food, int kilos){
        if(food instanceof LivingFlies || food instanceof SweetCorn){
            System.out.printf("%s turns it's head away in disgust.\n Badgers only eat Cat Chow!", this.name);
            return false;
        }
        super.eat(food, kilos);
        return true;
    }
}
