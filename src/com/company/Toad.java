package com.company;

public class Toad extends Animal {

    private int price = 5; // May have to put price in constructor!!


    public Toad(String name, String gender)
    {
        super(name, gender);

    }

    public int getPrice(){
        return price;
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
