package com.company;

import java.util.Scanner;

public class Shop {

    public void buyAnimal(Player player) {
        int choice = 0;
        boolean loop = true;
        do {
            Scanner scanner = new Scanner(System.in);
            print("\n".repeat(50) + "\nThe Dodgy Pet Shop\n------------------\n(Select 1-6. Press ENTER)");
            print("1. Toad, £5 \n2. Pike, £10 \n3. Pheasant, £20 \n4. Ferret, £40" +
                    " \n5. Badger, £60");
            try {
                choice = scanner.nextInt();
                loop = false;
            } catch (Exception e) {
                System.out.println("Only type numbers 1 - 5!");
            }
            if (choice < 1 || choice > 5) {
                print("Only type numbers 1 - 5!");
                loop = true;
            }
        } while (loop);
        addAnimal(player, choice);

        return;
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
        print(player.getName() + ", name your new pet: ");
        String petName = newScanner.nextLine();

        Animal newAnimal = switch (i) {
            case 1 -> new Toad(petName, gender);
            case 2 -> new Pike(petName, gender);
            case 3 -> new Pheasant(petName, gender);
            case 4 -> new Ferret(petName, gender);
            case 5 -> new Badger(petName, gender);
            default -> throw new IllegalStateException("Unexpected value");
        };
        // If player has enough money to buy animal, takingPayment returns true
        if (takingPayment(player, newAnimal)) {
            player.animals.add(newAnimal);
        }
    }

    public boolean takingPayment(Player player, Animal animal) {
        if (player.getMoney() < animal.getPrice()) {
            print("You don't have enough money to buy that pet. You have £" + player.getMoney() + " left.");
            return false;
        }
        player.setMoney(player.getMoney() - animal.getPrice());
        print("Hope you enjoy your " + animal.getClass().getSimpleName() + "!\nYou have £"
                + player.getMoney() + " left.");
        return true;
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
