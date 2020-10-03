package com.company;

public class Pike extends Animal {

    private int price = 10;

    public Pike(String name, String gender)
    {
        super(name, gender);
    }

    public int getPrice(){
        return price;
    }
}
