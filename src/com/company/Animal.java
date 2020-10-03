package com.company;

import java.util.Random;

abstract class Animal {

    Random random = new Random();

    protected String name;
    protected int health = 100;
    protected String gender;

    public Animal(String name){
        this.name = name;
        this.gender = setGender();

    }

    private String setGender(){
        int i = random.nextInt(2)+1;
        return (i == 1 ? "Female" : "Male");
    }

    public String getGender(){
        return gender;
    }

}
