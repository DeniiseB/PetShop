package com.company;

import java.io.Serializable;

public class LivingFlies extends Food implements Serializable {

    private int kiloprice = 6;
    private final String type = "Living Flies";
    private int kilos;

    public int getKiloprice(){
        return kiloprice;
    }
}
