package Model.Characters;

import Model.Characters.Character;
import Model.Characters.Hero;

public class Villain implements Character {

    private int x = 0;
    private int y = 0;
    private int attack;
    private int defense;
    private int hitPoints;

    public Villain() {
        attack = 10;
        defense = 10;
    }

    public Villain(Hero character) {
        attack = character.getLevel() * 10;
        defense = character.getLevel() * 10;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
