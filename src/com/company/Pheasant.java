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

//    public void eat(Food food){
//        if(food instanceof LivingFlies || food instanceof CatChow){
//            System.out.printf("%s shakes it's head in disgust.", this.name);
//            return;
//        }
//        super.eat(food);
//    }

}
