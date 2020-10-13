package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Serializable {

    Scanner scanner = new Scanner(System.in);

    private String filePath = "";
    public static ArrayList<Player> players = new ArrayList<>();
    public Player currentPlayer;
    public int maxRounds;
    int roundCounter = 0;
    boolean firstRound = true;
    public String newPlayer = "";
    Shop shop = new Shop();
    int playerIndex = 0;

    public Game() {
        // Write a new class Start? that starts off everything with the question
        // 1. Start a new game 2. Return to a saved if game
        // If 1 then new Game() otherwise ask for a file name, then deserialize the saved Game
        //var deserialized = Serializer.deserialize(filePath);

        // Add to the game menu: Save game
        // If chosen ask the user for a file/game name call Serializer.serialize(filename, this) - save the whole game instance to file

        // Make the player choose a decent number of rounds
        this.maxRounds = Dialogs.promptInt("Choose rounds (5-30)", 5, 30);
        addPlayer();
        // Sets the first player to the first name in players arraylist
        this.currentPlayer = players.get(0);
        gameMenu();

    }


    public void gameMenu() {
        boolean loop = true;
        do {
            setPlayerAndRounds(currentPlayer);
            if (roundCounter > maxRounds) {
                break;
            }
            if(currentPlayer.getMoney() <= 5 && currentPlayer.animals.size() == 0){
                print(currentPlayer.getName() + ", you don't have enough money to buy anything with, or any animals left. You're OUT!");
                players.remove(currentPlayer);
                try {
                    Thread.sleep(3000);
                }
                catch(Exception ignore){}
                continue;
            }
            print("\n".repeat(50) + "\nROUND " + roundCounter + "  " + currentPlayer.getName().toUpperCase()
                    + "  Money: £" + currentPlayer.getMoney() + "\n-----\nFood:  " + currentPlayer.foodInfo() + "\n-----");
            print("Pets:\n-----" + currentPlayer.animalsInfoReduceHealth());
            shop.boughtSoldAnything = 0;
            boolean roundPlayed = false;
            do {
                int choice = Dialogs.promptInt("\n-----\n1. Buy Animals " + "\n2. Buy Food \n3. Feed Animal " +
                        "\n4. Create Baby Animal \n5. Sell Animal \n6. Main Menu\n-----", 1, 6);

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
                        shop.sellAnimals(currentPlayer);
                        break;
                    case 6:
                        mainMenu();
                        break;
                }
            } while (shop.boughtSoldAnything == 0 && !roundPlayed);
        } while (loop);
        print(endStats());
    }


    public void mainMenu() {
        Dialogs.clear();
        int choice = Dialogs.promptInt("MAIN MENU\n---------\n(Pick 1-3, press ENTER)\n" +
                "1. Game rules\n2. Resume Game\n3. Save Game \n4. Exit game", 1, 4);
        switch (choice) {
            case 1:
                print("You have picked " + maxRounds + " rounds.\nWhen rounds are up, all players pets are sold" +
                        " for their health\nvalue times initial price and the player with most money wins.\nOnly one option" +
                        " in the menu can be picked per round per player.\nWhen a player doesn't have enough money to buy anything with," +
                        "\nor any animals left to sell, they're out\nDisclaimer: This game isn't based on real life situations.");
                Dialogs.promptString("Press ENTER to go back.");
                mainMenu();
            case 2:
                break;
            case 3:
                filePath = Dialogs.promptString("Name your game:");
                filePath += ".ser";
                try {
                    Dialogs.scanner = null;
                    this.scanner = null;
                    System.out.println("WHEN SAVING, NUMBER OF PLAYERS " + this.players.size());
                    Serializer.serialize(filePath, this);
                    Dialogs.scanner = new Scanner(System.in);
                    this.scanner = new Scanner(System.in);
                }
                catch(Exception ignore){}
                break;
            case 4:
                System.exit(0);
        }
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


    public void setPlayerAndRounds(Player player) {
//         Code for first round only, to keep first player
        if (firstRound) {
            this.firstRound = false;
            roundCounter++;
            return;
        }
        //MAKE SURE FIRST PLAYER STAYS AS FIRST PLAYER, (get.0???)
        if (players.size()==0){
            print("No winners here today! GAME OVER.");
            System.exit(0);
        }
        // Changes the index number in players to set currentPlayer to next player
        playerIndex = players.indexOf(currentPlayer);

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

    public String endStats() {
        String sentence = "";
        ArrayList<Player> losers = new ArrayList<>();
        for (Player player : players) {
            for (Animal animal : player.animals) {
                player.setMoney(player.getMoney() + animal.worth());
            }
            sentence += player.getName() + " has £" + player.getMoney() + "\n";
        }

        for (Player player : players) {
            for (Player player1 : players) {
                if (player.getMoney() == player1.getMoney()) {
                    continue;
                }
                if (player.getMoney() > player1.getMoney()) {
                    losers.add(player1);
                }
            }
        }
        players.removeAll(losers);
        for (Player player2 : players) {
            sentence += "\n" + player2.getName() + " WINS!";
        }

        return sentence;
    }


    private void print(String x) {
        // print a string if it is not empty
        if (!x.equals("")) {
            System.out.println(x);
        }
    }


}
