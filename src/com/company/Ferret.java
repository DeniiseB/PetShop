package com.company;

public class Ferret extends Animal {

    private int price = 40;

    public Ferret(String name, String gender)
    {
        super(name, gender);
    }

    public int getPrice(){
        return price;
    }

}
