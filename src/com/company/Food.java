package com.company;

import java.io.Serializable;

abstract class Food implements Serializable {

    protected int kiloprice;
    protected int kilos;
    protected String type;

    public int getKiloprice() {
        return kiloprice;
    }

    public int getKilos() {
        return kilos;
    }

    public void addKilos(int extraKilos) {
        this.kilos += extraKilos;
    }

    public void minusKilos(int minusKilos) {this.kilos -= minusKilos; }


}
