package com.company;

import java.util.Scanner;

public class Shop {
    Scanner scanner = new Scanner(System.in);



    public void buyAnimal(Player player){

        System.out.println("\nWelcome to The Dodgy Pet Shop. What animal are you looking to buy today?");
        System.out.println("1. Toad, £5 \n2. Pike, £10 \n3. Pheasant, £20 \n4. Ferret, £40" +
                " \n5. Badger, £60 \n6. Back");
        int choice = scanner.nextInt();
        addAnimal(player, choice);
        return;
    }

    public void addAnimal(Player player, int i){
        Scanner scanner1 = new Scanner(System.in);
        System.out.printf("%s, name your new pet: ", player.getName());
        String petName = scanner1.nextLine();  //fucking scanner!!!
        Animal animal = switch(i){
            case 1 -> new Toad(petName);
            case 2 -> new Pike(petName);
            case 3 -> new Pheasant(petName);
            case 4 -> new Ferret(petName);
            case 5 -> new Badger(petName);
            default -> throw new IllegalStateException("Unexpected value");
        };
        player.animals.add(animal);
        for(Animal animal1: player.animals){
            System.out.println(animal.getGender() + " " + animal.name);
        }
    }

    public void sellAnimal(){

    }

    public void buyFood(){

    }

}
