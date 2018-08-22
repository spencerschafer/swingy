package Model;

public class Villain extends Character{

    private int attack;
    private int defense;

    Villain() {
        attack = 10;
        defense = 10;
    }

    Villain(Character character) {
        attack = character.getLevel() * 10;
        defense = character.getLevel() * 10;
    }
}
