package Model;

import Model.Artifacts.Armor;
import Model.Artifacts.Helm;
import Model.Artifacts.Weapon;

import java.util.ArrayList;

public class Hero extends Character{

    private int attack;
    private int defense;
    private int hitPoints;

    Helm helmet;
    Armor armor;
    Weapon weapon;

    public Hero(String name, String type) {
        this.setName(name);
        this.setType(type);
        helmet = new Helm();
        armor = new Armor();
        weapon = new Weapon();
    }

    public Hero(ArrayList<String> list) {
        this.setName(list.get(0));
        this.setType(list.get(1));
        this.setLevel(Integer.parseInt(list.get(2)));
        this.setExperience(Integer.parseInt(list.get(3)));
        this.attack = Integer.parseInt(list.get(4));
        this.defense = Integer.parseInt(list.get(5));
        this.hitPoints = Integer.parseInt(list.get(6));
        this.helmet = new Helm(Integer.parseInt(list.get(7)));
        this.armor = new Armor(Integer.parseInt(list.get(8)));
        this.weapon = new Weapon(Integer.parseInt(list.get(9)));
        this.setMapLimit((this.getLevel() - 1) * 5 + 10 - (this.getLevel() % 2));
    }

    public Helm getHelmet() {
        return helmet;
    }

    public void setHelmet(Helm helmet) {
        this.helmet = helmet;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void printAttributes() {
        System.out.println("\nCharacter Stats");
        System.out.println("---------------");
        System.out.println("Name:       " + this.getName());
        System.out.println("Class:      " + this.getType());
        System.out.println("Level:      " + this.getLevel());
        System.out.println("Experience: " + this.getExperience());
        System.out.println("Attack:     " + this.attack);
        System.out.println("Defense:    " + this.defense);
        System.out.println("Hit Points: " + this.hitPoints);
        System.out.println();

        System.out.println("Character Artifacts");
        System.out.println("-------------------");
        System.out.println("Helmet:     +" + this.helmet.getHitPoints() + " Hit Points");
        System.out.println("Armor:      +" + this.armor.getDefense() + " Defense");
        System.out.println("Weapon:     +" + this.weapon.getAttack() + " Attack");
        System.out.println();
    }

    public ArrayList<String> saveAttributes() {
        ArrayList<String> list = new ArrayList<String>();

        list.add(this.getName());
        list.add(this.getType());
        list.add(Integer.toString(this.getLevel()));
        list.add(Integer.toString(this.getExperience()));
        list.add(Integer.toString(attack));
        list.add(Integer.toString(defense));
        list.add(Integer.toString(hitPoints));
        list.add(Integer.toString(helmet.getHitPoints()));
        list.add(Integer.toString(armor.getDefense()));
        list.add(Integer.toString(weapon.getAttack()));

        return list;
    }
}
