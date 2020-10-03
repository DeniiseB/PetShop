package com.company;

import java.util.Random;

abstract class Animal {

    Random random = new Random();

    protected String name;
    protected int health;
    protected String gender;

    public Animal(String name){
        this.name = name;
        this.health = 100;  // might have to change this
        this.gender = getGender();

    }

    private String getGender(){
        int i = random.nextInt(2)+1;
        return (i == 1 ? "Female" : "Male");
    }

}
