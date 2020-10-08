package com.company;

public class Pike extends Animal {

    private int price = 10; // May have to put price in constructor!!

    public Pike(String name, String gender)
    {
        super(name, gender);
    }

    public int getPrice(){
        return price;
    }

    public void eat(Food food, int kilos){
        super.eat(food, kilos);
    }

}
