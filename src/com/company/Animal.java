package com.company;

import java.util.Random;

abstract class Animal {

    Random random = new Random();

    protected String name;
    protected int health = 100;
    protected String gender;
    protected int price;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = setGender(gender);
    }

    private String setGender(String i){
        return (i.contains("f") ? "Female" : "Male");
    }

    private String setRandomGender(){
        int i = random.nextInt(2)+1;
        return (i == 1 ? "Female" : "Male");
    }

    public String getGender(){
        return gender;
    }

    public int getPrice(){
        return price;
    }
}
