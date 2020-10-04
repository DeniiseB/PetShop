package com.company;

import java.util.Scanner;

public class Shop {
    public boolean boughtAnything;

    public void buyAnimal(Player player) {
        int choice = 0;
        boolean loop = true;
        do {
            Scanner scanner = new Scanner(System.in);
            print("\n".repeat(5) + "\nThe Dodgy Pet Shop\n------------------\n(Select 1-6. Press ENTER)");
            print("1. Toad, £5 \n2. Pike, £10 \n3. Pheasant, £20 \n4. Ferret, £40" +
                    " \n5. Badger, £60 \n6. BACK (This option will change to next player if you have already" +
                    " bought a pet)");
            try {
                choice = scanner.nextInt();
                loop = false;
            } catch (Exception e) {
                System.out.println("Only type numbers 1 - 6!");
            }
            if (choice < 1 || choice > 6) {
                print("Only type numbers 1 - 6!");
                loop = true;
            }
        } while (loop);
        if (choice == 6) {
            System.out.println(boughtAnything);
            return;
        }
        addAnimal(player, choice);
        // After buying a pet, player gets option to buy more
        do {
            print("Buy another pet? (Select 1-2. Press ENTER.)\n1. Yes\n2. No");
            Scanner scanner = new Scanner(System.in);
            try {
                choice = scanner.nextInt();
                loop = false;
            } catch (Exception e) {
                print("Only type numbers 1-2!");
                loop = true;
            }
            if (choice < 1 || choice > 2) {
                print("Only type numbers 1 - 6!");
                loop = true;
            }
        } while (loop);

        if (choice == 1){
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
        // If takingPayment returns true, boughtPet changes to true
        if (takingPayment(player, newAnimal)) {
            player.animals.add(newAnimal);
            this.boughtAnything = true;
            return;
        }
    }

    public boolean takingPayment(Player player, Animal animal) {
        // If player has enough money to buy animal, takingPayment returns true
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
