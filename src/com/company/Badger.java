package com.company;

public class Badger extends Animal {

    private int price = 60;  // May have to put price in constructor!!

    public Badger(String name, String gender) {
        super(name, gender);
    }

    public int getPrice() {
        return price;
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
