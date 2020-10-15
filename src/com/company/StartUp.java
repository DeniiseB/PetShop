package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public final class StartUp implements Serializable {

    private static ArrayList<String> gameNames = new ArrayList<>();
    public static String filePath;

    private StartUp() {
    }

    static public void setGameNames(String name) {
        if (gameNames.contains(name)){
            int choice = Dialogs.promptInt(" already exists. Would you like to replace it? (1. Yes 2. No)", 1, 2);
            if(choice == 1){
                System.out.println(name + " saved.");
                return;
            }
            if (choice == 2) {
                String name2 = Dialogs.promptString("Type in a new name:");
                name2 += ".ser";
                setGameNames(name2);
            }
        }
        gameNames.add(name);
        System.out.println(name + " saved.");
    }

    static public void startUpMenu() {
        int choice = Dialogs.promptInt("1. New game\n2. Load game\n3. Exit", 1, 3);
        if (choice == 1) {
            new Game();
        }
        if (choice == 2) {
            filePath = chooseGame();
            var deserialized = Serializer.deserialize(filePath);
            var game = deserialized.equals(false) ? new Game() : (Game) deserialized;
            // get the old game running again
            if (!deserialized.equals(false)) {
                System.out.println("WELCOME BACK!");
                game.firstRound = true;
                game.gameMenu();
            }
        }
    }

    static public String chooseGame(){
        if(gameNames.isEmpty()){
            System.out.println("No saved games. Starting a new one...");
            return "";
        }
        int i = 0;
        for(String game: gameNames){
            System.out.println(++i + game);
        }
        int choice = Dialogs.promptInt("Pick game (number)", 1, gameNames.size());
        return gameNames.get(choice-1) + ".ser";

    }


}
