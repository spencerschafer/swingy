package Model.Characters;

import java.util.Random;

public class Villain extends Character {

    public Villain(String name, Hero hero) {
        Random rand = new Random();
        this.setName(name);
        this.setHitPoints((hero.getLevel() + 1) * 100);
        this.setDefense((hero.getLevel() + 1) * 10 - rand.nextInt(5) + 1);
        this.setAttack((hero.getLevel() + 1) * 10 - rand.nextInt(5) + 1);
    }
}
