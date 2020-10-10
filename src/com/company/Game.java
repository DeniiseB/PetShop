package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);

    public static ArrayList<Player> players = new ArrayList<>();
    public Player currentPlayer;
    public int maxRounds;
    int roundCounter = 0;
    boolean firstRound = true;
    public String newPlayer = "";
    Shop shop = new Shop();

    public Game() {
        // Make the player choose a decent number of rounds
        this.maxRounds = Dialogs.promptInt("Choose rounds (5-30)", 5, 30);
        addPlayer();
        // Sets the first player to the first name in players arraylist
        this.currentPlayer = players.get(0);
        mainMenu();

    }


    public void addPlayer() {
        // Method that adds players to players arraylist, with exception handler

        newPlayer = Dialogs.promptString("Enter name of player (0 to skip to Main Menu):");

        if (newPlayer.equals("0")) {
            // Making sure at least one player gets added
            if (players.size() == 0) {
                print("You must add at least one player!");
                addPlayer();
            }
            return;
        }
        this.players.add(new Player(newPlayer));
        // Makes sure that no more than 4 players are entered
        if (players.size() > 3) {
            return;
        }
        addPlayer();
    }


    public void mainMenu() {

        Dialogs.clear();
        int choice = Dialogs.promptInt("MAIN MENU\n---------\n(Pick 1-3, press ENTER)\n" +
                "1. Game rules\n2. START GAME\n3. Exit game", 1, 3);
        switch (choice) {
            case 1:
                print("Game rules:\n You have picked " + maxRounds + " rounds...ADD MORE.\n" +
                        "Disclaimer: This game isn't based on real life situations.");
                mainMenu();
            case 2:
                gameMenu();
                break;
            case 3:
                System.exit(0);
        }
    }

    public void gameMenu() {
        boolean loop = true;
        do {
            setPlayerAndRounds(currentPlayer);
            if (roundCounter > maxRounds) {
                break;
            }
            print("\n".repeat(50) + "\nROUND " + roundCounter + "  " + currentPlayer.getName().toUpperCase()
                    + "  Money: £" + currentPlayer.getMoney() + "\n-----\nFood:  " + currentPlayer.foodInfo() + "\n-----");
            print("Pets:\n-----" + currentPlayer.animalsInfoReduceHealth());
            shop.boughtAnything = 0;
            boolean roundPlayed = false;
            do {
                int choice = Dialogs.promptInt("\n-----\n1. Buy Animals " + "\n2. Buy Food \n3. Feed Animal " +
                        "\n4. Create Baby Animal \n5. Sell Animal \n6. EXIT GAME\n-----", 1, 6);

                switch (choice) {
                    case 1:
                        shop.buyAnimal(currentPlayer);
                        break;
                    case 2:
                        shop.buyFood(currentPlayer);
                        break;
                    case 3:
                        currentPlayer.feed();
                        break;
                    case 4:
                        roundPlayed = currentPlayer.createNewAnimals();
                        break;
                    case 5:
                        break;
                    case 6:
                        System.exit(0);
                }
            } while (shop.boughtAnything == 0 && !roundPlayed);
        } while (loop);
        print("GAME OVER");
        // Add method with end stats
    }

    public void setPlayerAndRounds(Player player) {
        // Code for first round only, to keep first player
        if (firstRound) {
            this.firstRound = false;
            roundCounter++;
            return;
        }

        // Changes the index number in players to set currentPlayer to next player
        int playerIndex = players.indexOf(currentPlayer);

        // If it's the last players turn, change next player to first player again
        if (playerIndex >= players.size() - 1) {
            currentPlayer = players.get(0);
            // Round counter goes up when every player has had one go each
            roundCounter++;
            return;
        }
        playerIndex++;
        currentPlayer = players.get(playerIndex);
    }


    private void print(String x) {
        // print a string if it is not empty
        if (!x.equals("")) {
            System.out.println(x);
        }
    }


}
