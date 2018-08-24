package Model.Characters;

public class Villain extends Character {

    public Villain(String name, Hero hero) {
        this.setName(name);

        if (hero.getLevel() != 0) {
            this.setAttack(hero.getLevel() * 10);
            this.setDefense(hero.getLevel() * 10);
        }
        else {
            this.setAttack(10);
            this.setDefense(10);
        }
    }
}
