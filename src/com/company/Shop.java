package com.company;

import java.util.Scanner;

public class Shop {
    Scanner scanner = new Scanner(System.in);

    public void buyAnimal(Player player) {

        print("\nWelcome to The Dodgy Pet Shop. What animal are you looking to buy today?");
        print("1. Toad, £5 \n2. Pike, £10 \n3. Pheasant, £20 \n4. Ferret, £40" +
                " \n5. Badger, £60 \n6. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        addAnimal(player, choice);
        return;
    }

    public void addAnimal(Player player, int i) {
        boolean loop = true;
        String gender = "";
        print("Female of male? (f/m)");
        while (loop) {
            gender = scanner.nextLine().toLowerCase();
            loop = false;
            if (!gender.equals("f") && !gender.equals("m")) {
                print("Only enter f or m!");
                loop = true;
            }
        }
        print(player.getName() + ", name your new pet: ");
        String petName = scanner.nextLine();

        Animal animal = switch (i) {
            case 1 -> new Toad(petName, gender);
            case 2 -> new Pike(petName, gender);
            case 3 -> new Pheasant(petName, gender);
            case 4 -> new Ferret(petName, gender);
            case 5 -> new Badger(petName, gender);
            default -> throw new IllegalStateException("Unexpected value");
        };
        player.animals.add(animal);

        for (Animal animal1 : player.animals) {
            System.out.println(animal.getGender() + " " + animal.name);
        }
    }

    public void sellAnimal() {

    }

    public void buyFood() {

    }

    private void print(String x) {
        // print a string if it is not empty
        if (!x.equals("")) {
            System.out.println(x);
        }
    }

}
