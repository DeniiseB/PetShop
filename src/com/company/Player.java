package com.company;

import java.util.ArrayList;

public class Player {

    private String name;
    public int money = 500;
    private ArrayList<Animal> animals = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public ArrayList getAnimals(){
        return animals;
    }

}
