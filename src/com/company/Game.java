package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);

    public ArrayList<Player> players = new ArrayList<>();;
    public int rounds;
    public String newPlayer = "";

    public Game() {
        System.out.println("Choose rounds (1-30)");
        this.rounds = scanner.nextInt();

        while(!newPlayer.equals("0")){      // Add limit to players (4)
        System.out.println("Enter name of player (0 to skip to Main Menu):");
        newPlayer = scanner.next();
        if(newPlayer.equals("0")){continue;}
        addPlayer(newPlayer);
        }

        System.out.println("Main Menu");
        for(Player player: players){
            System.out.println(player.getName());
        }

    }

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
    }


    public void menu() {


    }


}
