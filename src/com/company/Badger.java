package com.company;

public class Badger extends Animal {

    private int price = 60;

    public Badger(String name, String gender) {
        super(name, gender);
    }

    public int getPrice() {
        return price;
    }
}
