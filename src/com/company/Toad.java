package com.company;

public class Toad extends Animal {

    private int price = 5;


    public Toad(String name, String gender)
    {
        super(name, gender);

    }

    public int getPrice(){
        return price;
    }

    public void eat(Food food){
        if(food instanceof SweetCorn || food instanceof CatChow){
            System.out.printf("%s shakes it's head in disgust.", this.name);
            return;
        }
        super.eat(food);
    }


}
