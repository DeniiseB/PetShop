package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Game implements Serializable {

    public ArrayList<Player> players = new ArrayList<>();
    public int initialPlayers = 0;
    public Player currentPlayer;
    public int maxRounds;
    int roundCounter = 1;
    public boolean firstRound = true;
    public String newPlayer = "";
    Shop shop = new Shop();
    int playerIndex = 0;

    public Game() {
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
            //When rounds chosen by player are up, loop breaks and game ends
            if (roundCounter > maxRounds) {
                break;
            }
            print("\n".repeat(50) + "\nROUND " + roundCounter + "  " + currentPlayer.getName().toUpperCase()
                    + "  Money: £" + currentPlayer.getMoney() + "\n-----\nFood:  " + currentPlayer.foodInfo() + "\n-----");
            print("Pets:\n-----" + currentPlayer.animalsInfoReduceHealth(firstRound));
            // If player doesn't have the means to continue, they're out
            if (!checkPlayerStats(currentPlayer)) {
                continue;
            }
            //firstRound makes sure game stays where it left off when you save and exit game
            if (!firstRound) {
                currentPlayer.sickAnimals();
            } else {
                firstRound = false;
            }
            shop.boughtSoldAnything = 0;
            boolean roundPlayed = false;
            do {
                int choice = Dialogs.promptInt("\n-----\n1. Buy Animals " + "\n2. Buy Food \n3. Feed Animal " +
                        "\n4. Create Baby Animal \n5. Sell Animal \n6. See Veterinarian \n7. Main Menu(& Exit)\n-----", 1, 7);

                switch (choice) {
                    case 1:
                        shop.buyAnimal(currentPlayer);
                        break;
                    case 2:
                        shop.buyFood(currentPlayer);
                        break;
                    case 3:
                        roundPlayed = currentPlayer.feed();
                        break;
                    case 4:
                        roundPlayed = currentPlayer.createNewAnimals();
                        break;
                    case 5:
                        shop.sellAnimals(currentPlayer);
                        break;
                    case 6:
                        shop.seeVet(currentPlayer);
                        break;
                    case 7:
                        mainMenu();
                        break;
                }
            } while (shop.boughtSoldAnything == 0 && !roundPlayed && checkPlayerStats(currentPlayer));
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
                String filePath = Dialogs.promptString("Name your game:");
                filePath += ".ser";
                StartUp.setGameNames(filePath);
                try {
                    Dialogs.scanner = null;
                    Serializer.serialize(filePath, this);
                    Dialogs.scanner = new Scanner(System.in);
                } catch (Exception ignore) {
                }
                break;
            case 4:
                System.exit(0);
        }
    }


    public boolean checkPlayerStats(Player player) {
        if (initialPlayers > 1 && players.size() <= 1) {
            if (player.getMoney() < 5 && player.animals.size() == 0) {
                print("\nYou all lost. GAME OVER.");
            } else {
                print(endStats());
            }
            System.exit(0);
        }
        if (player.getMoney() < 5 && player.animals.size() == 0) {
            print("\n" + player.getName() + ", you don't have enough money to buy anything with, or any animals left. You're OUT!..");
            players.remove(player);
            try {
                Thread.sleep(3500);
            } catch (Exception ignore) {
            }
            return false;
        }
        return true;
    }


    public void addPlayer() {
        // Method that adds players to players arraylist
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
        initialPlayers++;
        // Makes sure that no more than 4 players are entered
        if (players.size() > 3) {
            return;
        }
        addPlayer();
    }


    public void setPlayerAndRounds(Player player) {
//         Code for first round of a new or loaded game, to keep first player
        if (firstRound) {
            return;
        }

        if (players.size() == 0) {
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
            sentence += "\n" + player.getName() + " has £" + player.getMoney() + "\n";
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
