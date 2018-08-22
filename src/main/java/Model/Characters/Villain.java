package Model.Characters;

public class Villain extends Character{

    public Villain(String name) {
        this.setName(name);
        this.setAttack(10);
        this.setDefense(10);
    }

    public Villain(String name, Hero hero) {
        this.setName(name);
        this.setAttack(hero.getLevel() * 10);
        this.setDefense(hero.getLevel() * 10);
    }
}
