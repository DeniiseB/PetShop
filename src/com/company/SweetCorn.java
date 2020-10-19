package com.company;

import java.io.Serializable;

public class SweetCorn extends Food implements Serializable {

    private int kiloprice = 8;
    private final String type = "Sweet Corn";
    private int kilos;

    public int getKiloprice(){
        return kiloprice;
    }
}
