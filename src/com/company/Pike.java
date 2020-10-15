package com.company;

import java.io.Serializable;

public class Pike extends Animal implements Serializable {

    public Pike(String name, String gender)
    {
        super(name, gender);
        price = 10;
        numberOfBabies = 4;
        maxAge = 7;
    }

    public boolean eat(Food food, int kilos){
        super.eat(food, kilos);
        return true;
    }

}
