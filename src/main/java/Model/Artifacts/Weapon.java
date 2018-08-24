package Model.Artifacts;

import Model.Characters.Hero;

import java.util.Random;

public class Weapon {
    private int attack;

    public Weapon(Hero hero) {
        Random rand = new Random();
        this.attack = (hero.getLevel() + 1) * 10 - (rand.nextInt(5) + 1);
    }

    public Weapon(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
