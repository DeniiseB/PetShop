package com.company;

import java.io.Serializable;

public final class StartUp implements Serializable {

    private static Game game;

    private StartUp(){
    }

    static public void startUpMenu(){
        int choice = Dialogs.promptInt("1. New game\n2. Load game\n3. Exit",1,3);
        if(choice == 1){
            game = new Game();
        }
        if(choice == 2){
            String filePath = Dialogs.promptString("Type file name:");
            var deserialized = Serializer.deserialize(filePath);

            game = deserialized.equals(false) ? new Game() : (Game) deserialized;
        }
    }


}
