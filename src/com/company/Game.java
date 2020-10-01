package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);

    public ArrayList<Player> players = new ArrayList<>();
    public Player currentPlayer;
    public int rounds;
    int roundCounter = 0;
    boolean firstRound = true;
    public String newPlayer = "";
    Shop shop = new Shop();

    public Game() {
        System.out.println("Choose rounds (5-30)"); // ADD TRY CATCH
        this.rounds = scanner.nextInt();

        addPlayer();
        mainMenu();

    }

    public void addPlayer() {
        // Method that adds players to players arraylist
        System.out.println("Enter name of player (0 to skip to Main Menu):");
        newPlayer = scanner.next();     // TRY CATCH??
        if (newPlayer.equals("0")) {
            // Making sure at least one player gets added
            if (players.size() == 0) {
                System.out.println("You must add at least one player!");
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
                System.out.println("Game rules:\n You have picked " + rounds + " rounds...ADD MORE.\n");
                mainMenu();
            case 2:
                System.out.println("\n".repeat(50) + "**NEW GAME STARTED**\n (Choose 1-5. Press ENTER.)\n");
                gameMenu();
                break;
            case 3:
                System.exit(0);

        }
    }

    public void gameMenu() { // add player

        while (roundCounter < rounds) {
            setPlayer(currentPlayer);
            System.out.println("ROUND " + roundCounter + "\n" + currentPlayer.getName() + ":\n1. Buy Animals " +
                    "\n2. Buy Food \n3. Feed Animal \n4. Create Baby Animal" +
                    "\n5. Sell Animal \n88. EXIT GAME");  // ADD player animals and money
            int choice = scanner.nextInt();  // ADD TRY CATCH
            switch (choice) {
                case 1:
                    shop.buyAnimal(currentPlayer); // add player
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.exit(0);
            }
        }
        System.out.println("GAME OVER");
        // Add method with end stats
    }

    public Player setPlayer(Player player){
        // Sets the first player to be first player in arraylist players
        if (firstRound) {
            firstRound = false;
            this.currentPlayer = players.get(0);
            return currentPlayer;
        }
        // Changes the index number in players to set currentPlayer to next player
        int playerIndex = players.indexOf(currentPlayer);
        // If it's the last players turn, change next player to first player again
        if (playerIndex >= players.size()-1){
            currentPlayer = players.get(0);
            // Round counter goes up when every player has had one go each
            roundCounter++;
            return currentPlayer;
        }
        playerIndex++;
        currentPlayer = players.get(playerIndex);
        return currentPlayer;
    }

//    public Player playerTurn(Player currentPlayer){
//        // Changes the index number in players to set currentPlayer to next player
//        int playerIndex = players.indexOf(currentPlayer);
//        // If it's the last players turn, change next player to first player again
//        if (playerIndex >= players.size()-1){
//            currentPlayer = players.get(0);
//            return currentPlayer;
//        }
//        playerIndex++;
//        currentPlayer = players.get(playerIndex);
//        return currentPlayer;
//    }

}
