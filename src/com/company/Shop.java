package com.company;

import java.util.Scanner;

public class Shop {
    Scanner scanner = new Scanner(System.in);




    public void buyAnimal(Player player){
        System.out.println("Welcome to The Dodgy Pet Shop. What animal are you looking to buy today?");
        System.out.println("1. Toad, £5 \n2. Pike, £10 \n3. Pheasant, £20 \n4. Ferret, £40" +
                " \n5. Badger, £60 \n6. Back");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Current player:" + player.getName());
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;

        }
        return;
    }

    public void sellAnimal(){

    }

    public void buyFood(){

    }

}
