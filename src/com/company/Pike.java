package com.company;

public class Pike extends Animal {

    public Pike(String name, String gender)
    {
        super(name, gender);
        price = 10;
    }

    public boolean eat(Food food, int kilos){
        super.eat(food, kilos);
        return true;
    }


}
