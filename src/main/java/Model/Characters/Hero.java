package Model.Characters;

import Controller.Main;
import Model.Artifacts.Armor;
import Model.Artifacts.Helm;
import Model.Artifacts.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

public class Hero extends Character {
    private int mapLimit;

    private String type;
    private int level = 0;
    private int experience = 0;

    private Helm helmet;
    private Armor armor;
    private Weapon weapon;

    private int previousX;
    private int previousY;

    private final int MAX_LEVEL = 1;

    public Hero () {
        this.setName("Default");
        this.setType("Archer");
        this.mapLimit = (this.level - 1) * 5 + 10 - (this.level % 2);
        helmet = new Helm(this);
        armor = new Armor(this);
        weapon = new Weapon(this);
        if (type.equalsIgnoreCase("warrior")) {
            this.setDefense(armor.getDefense() + 20);
            this.setAttack(weapon.getAttack());
            this.setHitPoints(this.getHitPoints() + helmet.getHitPoints() + 50);
        }
        else if (type.equalsIgnoreCase("archer")) {
            this.setDefense(armor.getDefense());
            this.setAttack(weapon.getAttack() + 20);
            this.setHitPoints(this.getHitPoints() + helmet.getHitPoints());
        }
    }
    
    public Hero(String name, String type) {
        this.setName(name);
        this.setType(type);
        this.mapLimit = (this.level - 1) * 5 + 10 - (this.level % 2);
        helmet = new Helm(this);
        armor = new Armor(this);
        weapon = new Weapon(this);
        if (type.equalsIgnoreCase("warrior")) {
            this.setDefense(armor.getDefense() + 20);
            this.setAttack(weapon.getAttack());
            this.setHitPoints(this.getHitPoints() + helmet.getHitPoints() + 50);
        }
        else if (type.equalsIgnoreCase("archer")) {
            this.setDefense(armor.getDefense());
            this.setAttack(weapon.getAttack() + 20);
            this.setHitPoints(this.getHitPoints() + helmet.getHitPoints());
        }
    }

    public Hero(ArrayList<String> list) {
        this.setName(list.get(0));
        this.setType(list.get(1));
        this.setLevel(Integer.parseInt(list.get(2)));
        this.setExperience(Integer.parseInt(list.get(3)));

        this.helmet = new Helm(Integer.parseInt(list.get(7)));
        this.armor = new Armor(Integer.parseInt(list.get(8)));
        this.weapon = new Weapon(Integer.parseInt(list.get(9)));

        this.setHitPoints(Integer.parseInt(list.get(6)) + this.helmet.getHitPoints());
        this.setDefense(Integer.parseInt(list.get(5)) + this.armor.getDefense());
        this.setAttack(Integer.parseInt(list.get(4)) + this.weapon.getAttack());

        this.setMapLimit((this.getLevel() - 1) * 5 + 10 - (this.getLevel() % 2));
    }

    public boolean move() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println();
        //TODO: ignore case may not need both checks (i.e ignore case "s" includes "S" and "s"

        setPreviousPosition(this.getX(), this.getY());

        if (input.equalsIgnoreCase("w")) {
            if (this.getY() != 0)
                this.setY(this.getY() - 1);
        } else if (input.equalsIgnoreCase("a")) {
            if (this.getX() != 0)
                this.setX(this.getX() - 1);
        } else if (input.equalsIgnoreCase("s")) {
            if (this.getY() != mapLimit - 1)
                this.setY(this.getY() + 1);
        } else if (input.equalsIgnoreCase("d")) {
            if (this.getX() != mapLimit - 1)
                this.setX(this.getX() + 1);
        } else if (input.equalsIgnoreCase("z")) {
            printAttributes();
        } else if (input.equalsIgnoreCase("x")) {
            printKey();
        } else if (input.equalsIgnoreCase("c")) {
            printControls();
        } else if (input.equalsIgnoreCase("q")) {
            return false;
        }
        return true;
    }

    public void increaseExperience() {
        this.experience += ((this.level + 1) * 100);

        double total = (this.level * 1000) + (Math.pow((this.level - 1), 2) * 450);
        if  (this.experience > total) {
            if (this.level < MAX_LEVEL) {
                this.level++;
                this.experience -= total;
            }
            else
                this.experience = 0;
        }
    }

    public void levelUp() {
        if (this.level < MAX_LEVEL )
        this.level++;
    }

    public void printAttributes() {
        System.out.println("\nCharacter Stats");
        System.out.println("---------------");
        System.out.println("Name:       " + this.getName());
        System.out.println("Class:      " + this.getType());
        System.out.println("Level:      " + this.getLevel());
        System.out.println("Experience: " + this.getExperience());
        System.out.println("Attack:     " + this.getAttack());
        System.out.println("Defense:    " + this.getDefense());
        System.out.println("Hit Points: " + this.getHitPoints());
        System.out.println();

        System.out.println("Character Artifacts");
        System.out.println("-------------------");
        System.out.println("Helmet:     +" + this.helmet.getHitPoints() + " Hit Points");
        System.out.println("Armor:      +" + this.armor.getDefense() + " Defense");
        System.out.println("Weapon:     +" + this.weapon.getAttack() + " Attack");
        System.out.println();
    }

    private void printControls() {
        System.out.println("\nControls");
        System.out.println("--------");
        System.out.println("W - Up");
        System.out.println("A - Left");
        System.out.println("S - Down");
        System.out.println("D - Right");
        System.out.println();
        System.out.println("Q - Main Menu");
        System.out.println("Z - Hero Attributes");
        System.out.println("X - Map Key");
        System.out.println("C - Controls");
        System.out.println();
    }

    public void printKey() {
        System.out.println("\nMap Key");
        System.out.println("-------");
        System.out.println("o - Hero");
        System.out.println("x - Villain");
        System.out.println(". - Empty");
        System.out.println();
    }

    public ArrayList<String> saveAttributes() {
        ArrayList<String> list = new ArrayList<>();

        list.add(this.getName());
        list.add(this.getType());
        list.add(Integer.toString(this.getLevel()));
        list.add(Integer.toString(this.getExperience()));
        list.add(Integer.toString(this.getAttack()));
        list.add(Integer.toString(this.getDefense()));
        list.add(Integer.toString(this.getHitPoints()));
        list.add(Integer.toString(helmet.getHitPoints()));
        list.add(Integer.toString(armor.getDefense()));
        list.add(Integer.toString(weapon.getAttack()));

        return list;
    }

    public void setCharacterPosition(int x, int y) {
        this.previousX = x;
        this.previousY = y;
        this.setX(x);
        this.setY(y);
    }

    public void setPreviousPosition(int x, int y) {
        this.previousX = x;
        this.previousY = y;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public int getMapLimit() {
        return mapLimit;
    }

    public void setMapLimit(int mapLimit) {
        this.mapLimit = mapLimit;
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

    public int getMAX_LEVEL () {
        return MAX_LEVEL;
    }

    public Helm getHelmet() {
        return helmet;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void newHelm(Helm helmet) {
        int updateHitPoints = (this.getHitPoints() - this.helmet.getHitPoints()) + helmet.getHitPoints();
        this.helmet = helmet;
        this.setHitPoints(updateHitPoints);
    }

    public void newArmor(Armor armor) {
        int updateDefense = (this.getDefense() - this.armor.getDefense()) + armor.getDefense();
        this.armor = armor;
        this.setDefense(updateDefense);
    }

    public void newWeapon(Weapon weapon) {
        int updateAttack = (this.getAttack() - this.weapon.getAttack()) + weapon.getAttack();
        this.weapon = weapon;
        this.setAttack(updateAttack);
    }
}
