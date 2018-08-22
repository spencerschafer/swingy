package Model.Characters;

import Controller.Main;
import Model.Artifacts.Armor;
import Model.Artifacts.Helm;
import Model.Artifacts.Weapon;
import Model.Characters.Character;

import java.util.ArrayList;
import java.util.Scanner;

public class Hero implements Character {
    private int x = 0;
    private int y = 0;
    private int mapLimit;

    private String name; // May not be relevant in all characters i.e Villains
    private String type; // May not be relevant in all characters i.e Villains
    private int level = 0;
    private int experience = 0;
    private int attack;
    private int defense;
    private int hitPoints;

    private Helm helmet;
    private Armor armor;
    private Weapon weapon;


    public Hero() {
        this.name = "Default";
        this.type = "Default";
        this.mapLimit = (this.level - 1) * 5 + 10 - (this.level % 2);
    }

    public Hero(String name, String type) {
        this.setName(name);
        this.setType(type);
        this.mapLimit = (this.level - 1) * 5 + 10 - (this.level % 2);
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

    public void move() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println();
        if (input.equalsIgnoreCase("w") || input.equalsIgnoreCase("W")) {
            if (this.y != 0)
                this.y -= 1;
        } else if (input.equalsIgnoreCase("a") || input.equalsIgnoreCase("A")) {
            if (this.x != 0)
                this.x -= 1;
        } else if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("S")) {
            if (this.y != mapLimit - 1)
                this.y += 1;
        } else if (input.equalsIgnoreCase("d") || input.equalsIgnoreCase("D")) {
            if (this.x != mapLimit - 1)
                this.x += 1;
        } else if (input.equalsIgnoreCase("z") || input.equalsIgnoreCase("Z")) {
            printAttributes();
        } else if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("X")) {
            printKey();
        } else if (input.equalsIgnoreCase("c") || input.equalsIgnoreCase("C")) {
            printControls();
        } else if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("Q")) {
            Main.mainMenu();
        }
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

    public void printControls() {
        System.out.println("\nControls");
        System.out.println("--------");
        System.out.println("W - Up");
        System.out.println("A - Left");
        System.out.println("S - Down");
        System.out.println("D - Right");
        System.out.println();
        System.out.println("Q - Main Menu");
        System.out.println("Z - Hero Stats");
        System.out.println("X - Map Key");
        System.out.println("C - Controls");
        System.out.println();
    }

    public void printKey() {
        System.out.println("\nMap Key");
        System.out.println("-------");
        System.out.println("o - Hero");
        System.out.println("| - Villain");
        System.out.println(". - Empty");
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

    public void setCharacterPosition(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getMapLimit() {
        return mapLimit;
    }

    public void setMapLimit(int mapLimit) {
        this.mapLimit = mapLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
