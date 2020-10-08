package com.company;

public class Badger extends Animal {

    private int price = 60;

    public Badger(String name, String gender) {
        super(name, gender);
    }

    public int getPrice() {
        return price;
    }

    public void eat(Food food){
        if(food instanceof LivingFlies || food instanceof SweetCorn){
            System.out.printf("%s shakes it's head in disgust.", this.name);
            return;
        }
        super.eat(food);
    }
}
