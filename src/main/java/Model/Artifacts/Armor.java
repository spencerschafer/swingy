package Model.Artifacts;


import Model.Characters.Hero;

import java.util.Random;

public class Armor {
    private int defense;

    public Armor(Hero hero) {
        Random rand = new Random();
        this.defense = (hero.getLevel() + 1) * 10 - (rand.nextInt(5) + 1);
    }

    public Armor(int defense) {
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
