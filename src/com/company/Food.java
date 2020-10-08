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

    public void feed(Player player) {

        if(player.animals.size() == 0) {
            System.out.println("You don't have any pets!");
            return;
        }
        System.out.println(player.animalsInfo());
        int choice = Dialogs.promptInt("Pick pet(number) to feed \n", 0,player.animals.size());

         Animal animal = player.animals.get(choice - 1);

        int kilos = Dialogs.promptInt("Enter kilos to feed:" + animal.name +
                ":", 0, this.kilos);
        animal.eat(this, kilos);
    }


}
