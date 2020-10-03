package com.company;

public class Pheasant extends Animal {

    private int price = 20;

    public Pheasant(String name, String gender)
    {
        super(name, gender);
    }

    public int getPrice(){
        return price;
    }

}
