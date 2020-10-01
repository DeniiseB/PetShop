package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);

    public ArrayList<Player> players = new ArrayList<>();
    public int rounds;
    public String newPlayer = "";

    public Game() {
        System.out.println("Choose rounds (5-30)");
        this.rounds = scanner.nextInt();

        addPlayer();

        mainMenu();


    }

    public void addPlayer() {
        // Method that adds players to players arraylist
        System.out.println("Enter name of player (0 to skip to Main Menu):");
        newPlayer = scanner.next();

        if (newPlayer.equals("0")) {
            // Making sure at least one player gets added
            if (players.size() == 0) {
                System.out.println("You must add at least one player!");
                addPlayer();
            }
            return;
        }

        this.players.add(new Player(newPlayer));
        if (players.size() > 3) {
            return;
        }

        addPlayer();
    }

    public void mainMenu() {

        System.out.println("\nMAIN MENU\n---------\n(Pick 1-3, press ENTER)");
        System.out.println("1. Game rules\n2. Start Game\n3. Exit game");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Game rules:\n You have picked " + rounds + " rounds...ADD MORE.\n");
                mainMenu();
            case 2:
                System.out.println("Start game method");
                break;
            case 3:
                System.exit(0);

        }
    }

    public void gameMenu() {

    }


}
