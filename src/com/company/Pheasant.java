package com.company;

public class Pheasant extends Animal {

    private int price = 20; // May have to put price in constructor!!

    public Pheasant(String name, String gender)
    {
        super(name, gender);
    }

    public int getPrice(){
        return price;
    }

    public void eat(Food food, int kilos){
        if(food instanceof LivingFlies || food instanceof CatChow){
            System.out.printf("%s turns it's head away in disgust.\n", this.name);
            return;
        }
        super.eat(food, kilos);
    }

}
