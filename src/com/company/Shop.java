package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop implements Serializable {
    public int boughtSoldAnything = 0;


    public void buyAnimal(Player player) {
        int choice = 0;
        Dialogs.clear();
        choice = Dialogs.promptInt("\nThe Dodgy Pet Shop\n------------------\n(Select 1-6. Press ENTER)"
                + "\n1. Toad, £5 \n2. Pike, £10 \n3. Pheasant, £20 \n4. Ferret, £40 \n5. Badger, £60" +
                " \n6. BACK (This option will change to next player if you have already bought a pet)", 1, 6);

        if (choice == 6) {
            return;
        }

        addAnimal(player, choice);

        // After buying a pet, player gets option to buy more
        choice = Dialogs.promptInt("Buy another pet? (Select 1-2. Press ENTER.)\n1. Yes\n2. No", 1, 2);

        if (choice == 1) {
            buyAnimal(player);
        }
    }

    public void addAnimal(Player player, int i) {
        Scanner newScanner = new Scanner(System.in);
        boolean loop = true;
        String gender = "";
        print("Female of male? (f/m)");
        while (loop) {
            gender = newScanner.nextLine().toLowerCase();
            loop = false;
            if (!gender.equals("f") && !gender.equals("m")) {
                print("Only enter f or m!");
                loop = true;
            }
        }
        String petName = Dialogs.promptString(player.getName() + ", name your new pet: ");

        Animal newAnimal = switch (i) {
            case 1 -> new Toad(petName, gender);
            case 2 -> new Pike(petName, gender);
            case 3 -> new Pheasant(petName, gender);
            case 4 -> new Ferret(petName, gender);
            default -> new Badger(petName, gender);
        };
        // If takingPayment returns true, boughtAnything changes to true
        if (takingAnimalPayment(player, newAnimal)) {
            player.animals.add(newAnimal);
            this.boughtSoldAnything++;
        }
    }

    public boolean takingAnimalPayment(Player player, Animal animal) {
        // If player has enough money to buy animal, takingPayment returns true
        if (player.getMoney() < animal.getPrice()) {
            print("You don't have enough money to buy that pet. You have £" + player.getMoney() + " left.");
            return false;
        }
        player.setMoney(player.getMoney() - animal.getPrice());
        print("You have £" + player.getMoney() + " left.");
        return true;
    }


    public void buyFood(Player player) {
        int choice = 0;
        Dialogs.clear();
        choice = Dialogs.promptInt("\nThe Dodgy Pet Shop\n------------------\n(Select 1-4. Press ENTER)\n" +
                "1. Living Flies, £7/Kg \n2. Sweet Corn, £9/Kg \n3. Cat Chow, £12/Kg \n4. BACK" +
                " (This option will change to next player if you have already bought food)", 1, 4);

        if (choice == 4) {
            return;
        }

        addFood(player, choice);
        // After buying food, player gets option to buy more
        choice = Dialogs.promptInt("Buy more food? (Select 1-2. Press ENTER.)\n1. Yes\n2. No", 1, 2);

        if (choice == 1) {
            buyFood(player);
        }
    }


    public void addFood(Player player, int choice) {

        int kilos = Dialogs.promptInt("How many kilos?", 0, 10000);

        Food tempFood = switch (choice) {
            case 1 -> player.livingFlies;
            case 2 -> player.sweetCorn;
            default -> player.catChow;
        };

        if (takingFoodPayment(player, tempFood, kilos)) {
            tempFood.addKilos(kilos);
            boughtSoldAnything++;
        }
    }


    public boolean takingFoodPayment(Player player, Food food, int kilos) {
        // If player has enough money to buy food, takingPayment returns true
        int finalPrice = food.getKiloprice() * kilos;
        if (player.getMoney() < finalPrice) {
            print("You don't have enough money to buy that.\nYou have £" + player.getMoney() + " left and" +
                    " the total price comes to £" + finalPrice);
            return false;
        }
        player.setMoney(player.getMoney() - finalPrice);
        print("\nYou have £" + player.getMoney() + " left.");
        return true;
    }

    public void sellAnimals(Player player) {
        if (player.animals.size() == 0) {
            print("You have no pets to sell.");
            return;
        }
        print(player.animalsInfo());
        int choice = Dialogs.promptInt("--Select pet(number) to sell--\n" +
                "(0. BACK *will skip to next player if already sold something*)", 0, player.animals.size());
        if (choice == 0) {
            return;
        }
        Animal animal = player.animals.get(choice - 1);
        if (animal.isSick) {
            print("You can't sell a sick animal.");
            return;
        }

        player.setMoney(player.getMoney() + animal.worth());
        print(animal.name + " just made you £" + animal.worth() + "! You now have £" + player.getMoney() + " in total.");

        player.animals.remove(animal);
        this.boughtSoldAnything++;

        choice = Dialogs.promptInt("Sell another pet? (Select 1-2. Press ENTER.)\n1. Yes\n2. No", 1, 2);

        if (choice == 1) {
            sellAnimals(player);
        }
    }

    public void seeVet(Player player) {
        //Player can see the vet and then go back to menu and do something else in same round
        int counter = 0;
        ArrayList<Animal> sickAnimals = new ArrayList<>();
        for (Animal animal : player.animals) {
            if (animal.isSick) {
                sickAnimals.add(animal);
                print(++counter + ". " + animal.name + " " + animal.getClass().getSimpleName());
            }
        }
        int choice = Dialogs.promptInt("--Select pet(number) to treat--\n(0. BACK)", 0, counter);
        if (choice == 0) {
            return;
        }
        Animal animal = sickAnimals.get(choice - 1);

        if (player.getMoney() < animal.worth()) {
            choice = Dialogs.promptInt("You don't have enough money to treat this animal. Skip round? (1.Yes/2.No)",1,2);
            if(choice == 1){
                this.boughtSoldAnything++;
            }
            return;
        }
        player.setMoney(player.getMoney() - animal.worth());

        double number = Math.random();
        animal.isSick = (number < 0.5);

        if (animal.isSick) {
            print("VET: I'm very sorry, " + animal.name + " died...");
            player.animals.remove(animal);
        } else {
            print(animal.name + " is feeling better!");
        }

        print("That came to a total of £" + animal.worth());
        try {
            Thread.sleep(3000);
        } catch (Exception ignore) {
        }
    }


    private void print(String x) {
        // print a string if it is not empty
        if (!x.equals("")) {
            System.out.println(x);
        }
    }

}
