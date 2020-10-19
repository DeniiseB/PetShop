package com.company;

import java.io.Serializable;

public class CatChow extends Food implements Serializable {

    private int kiloprice = 10;
    private final String type = "Cat Chow";
    private int kilos;

    public int getKiloprice(){
        return kiloprice;
    }

}
