package com.company;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public final class StartUp implements Serializable {

    private static ArrayList<String> gameNames;
    private static Path textFilePath = Paths.get("saved-games.txt");
    public static String filePath;

    private StartUp() {
    }

    static public void setGameNames(String name) {
        if (gameNames.contains(name)){
            int choice = Dialogs.promptInt(" already exists. Would you like to replace it? (1. Yes 2. No)", 1, 2);
            if (choice == 1){
                gameNames.remove(name);
            }
            if (choice == 2) {
                String name2 = Dialogs.promptString("Type in a new name:");
                name2 += ".ser";
                setGameNames(name2);
            }
        }
        gameNames.add(name);
        try{
        Files.write(textFilePath,gameNames,StandardCharsets.UTF_8);
        }catch(Exception ignore){}
        System.out.println(name + " saved.");
    }

    static public void startUpMenu() {
        readTextFilesToList();
        int choice = Dialogs.promptInt("1. New Game\n2. Load Game\n3. Exit", 1, 3);
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
        if (choice == 3){
            System.exit(0);
        }
    }

    static public String chooseGame(){
        if(gameNames.isEmpty()){
            System.out.println("No saved games. Starting a new one...");
            return "";
        }
        int i = 0;
        for(String game: gameNames){
            System.out.println(++i + ". " + game);
        }
        int choice = Dialogs.promptInt("Pick game (number)", 1, gameNames.size());
        return gameNames.get(choice-1);

    }

    static private void readTextFilesToList(){
        try {
            String content = Files.readString(textFilePath, StandardCharsets.UTF_8);
            gameNames = new ArrayList<>(Arrays.asList(content.replace("\r", "").split("\n")));
            gameNames.remove("");
        }catch (Exception e){
            gameNames = new ArrayList<>();
        }
    }

}
