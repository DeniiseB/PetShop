package com.company;

import java.util.ArrayList;

public class Player {

    private String name;
    public int money = 500;
    public ArrayList<Animal> animals;

    public Player(String name){

        this.name = name;
        this.animals = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
//
//    public ArrayList getAnimals(){
//        return animals;
//    }

}
