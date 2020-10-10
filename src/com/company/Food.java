package com.company;

abstract class Food {

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


}
