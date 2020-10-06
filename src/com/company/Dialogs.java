package com.company;

import java.util.Scanner;

public abstract class Dialogs {

    static private Scanner scanner = new Scanner(System.in);

    static public void clear(){
        System.out.println("\n".repeat(60));
    }

    static public String promptString(String question){
        System.out.println(question);
        return scanner.nextLine();
    }

    static public int promptInt(String question, int min, int max){
        var num = min - 1;
        try{
            num = Integer.parseInt(promptString(question));

        }
        catch(Exception ignore){}
        return num < min || num > max ? promptInt(question, min, max) : num;
    }



}
