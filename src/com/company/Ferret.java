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

    public void eat(Food food){
        if(food instanceof LivingFlies){
            System.out.printf("%s shakes it's head in disgust.", this.name);
            return;
        }
        super.eat(food);
    }

}
