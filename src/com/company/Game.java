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

        chooseRounds();
        addPlayer();
        // Sets the first player to the first name in players arraylist
        this.currentPlayer = players.get(0);
        mainMenu();

    }

    public void chooseRounds() {
        // Method to make the player choose a decent number of rounds
        print("Choose rounds (5-30)");
        try {
            this.maxRounds = scanner.nextInt();
        } catch (Exception e) {
            print("Only type in a number between 5-30!");
            scanner.next();
            chooseRounds();
        }
        if (maxRounds < 5 || maxRounds > 30) {
            print("Only numbers between 5 and 30 allowed.");
            chooseRounds();
        }
    }


    public void addPlayer() {
        // Method that adds players to players arraylist, with exception handler
        print("Enter name of player (0 to skip to Main Menu):");
        try {
            newPlayer = scanner.next();
        }
        catch (Exception e){
            print("Only type your name with letters, please!");
            scanner.next();
            addPlayer();
        }
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

        System.out.println("\nMAIN MENU\n---------\n(Pick 1-3, press ENTER)"); // TRY CATCH??
        System.out.println("1. Game rules\n2. Start Game\n3. Exit game");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                print("Game rules:\n You have picked " + maxRounds + " rounds...ADD MORE.\n");
                mainMenu();
            case 2:
                print("\n".repeat(50) + "**NEW GAME STARTED**\n (Choose 1-5. Press ENTER.)\n");
                gameMenu();
                break;
            case 3:
                System.exit(0);

        }
    }

    public void gameMenu() { // add player
        boolean loop = true;
        do {
            setPlayer(currentPlayer);
            if(roundCounter > maxRounds){break;}

            print("ROUND " + roundCounter + "\n" + currentPlayer.getName() + ":\n1. Buy Animals " +
                    "\n2. Buy Food \n3. Feed Animal \n4. Create Baby Animal" +
                    "\n5. Sell Animal \n88. EXIT GAME" );  // ADD player animals and money

            int choice = scanner.nextInt();  // ADD TRY CATCH
            switch (choice) {
                case 1:
                    shop.buyAnimal(currentPlayer);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 88:
                    System.exit(0);
            }
        } while (loop);
        print("GAME OVER");
        // Add method with end stats
    }

    public void setPlayer(Player player) {
        // Code for first round only, to keep first player
        if(firstRound){
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

    private void print(String x){
        // print a string if it is not empty
        if(!x.equals("")){ System.out.println(x); }
    }

}
