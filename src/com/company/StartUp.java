package com.company;

import java.io.Serializable;

public final class StartUp implements Serializable {

    private StartUp(){
    }

    static public void startUpMenu(){
        int choice = Dialogs.promptInt("1. New game\n2. Load game\n3. Exit",1,3);
        if(choice == 1){
            new Game();
        }
        if(choice == 2){
            String filePath = Dialogs.promptString("Type file name:") + ".ser";
            var deserialized = Serializer.deserialize(filePath);
            var game = deserialized.equals(false) ? new Game() : (Game) deserialized;
            // get the old game running again
            if(!deserialized.equals(false)){
                System.out.println("SAVED GAME PLAYERS COUNT " + game.players.size());
                System.out.println("SAVED GAME CURRENT PLAYER " + game.currentPlayer);
                System.out.println("SAVED GAME ROUND COUNT " + game.roundCounter);
                game.gameMenu();
            }
        }
    }


}
