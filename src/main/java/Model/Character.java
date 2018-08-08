package Model;

import Controller.Main;
import java.util.ArrayList;
import java.util.Scanner;

public class Character {
    private int x = 0;
    private int y = 0;




//  Attributes
    private String name; // May not be relevant in all characters i.e Villians
    private String type; // May not be relevant in all characters i.e Villians
    private int level = 0;
    private int experience = 0;
    private int attack = 30;
    private int defense = 10;
    private int hitPoints = 100;

//  TODO
//  Artifacts
    Artifact Weapon = null; // increases attack
    Artifact Armor = null; // increases defense
    Artifact Helm = null; // increases hit points

//  New character will be level 0 by default
    public Character() {
        this.x = 0;
        this.y = 0;
        this.name = "Default";
        this.type = "Default";
    }

    public Character(String name, String type){
        this.x = 0;
        this.x = 0;
        this.name = name;
        this.type = type;
    }

    public Character(ArrayList<String> list) {
        this.x = 0;
        this.y = 0;
        this.name = list.get(0);
        this.type = list.get(1);
        this.level = Integer.parseInt(list.get(2));
        this.experience = Integer.parseInt(list.get(3));
        this.attack = Integer.parseInt(list.get(4));
        this.defense = Integer.parseInt(list.get(5));
        this.hitPoints = Integer.parseInt(list.get(6));
    }

    public void move() {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        System.out.println();
        if (str.equalsIgnoreCase("w")) {
            this.y -= 1;
        } else if (str.equalsIgnoreCase("s")) {
            this.y += 1;
        } else if (str.equalsIgnoreCase("a")) {
            this.x -= 1;
        } else if (str.equalsIgnoreCase("d")) {
            this.x += 1;
        }
        else if (str.equalsIgnoreCase("z")) {
            printAttributes();
        }
        else if (str.equalsIgnoreCase("c")) {
            printControls();
        }
        else if (str.equalsIgnoreCase("q")) {
            Main.mainMenu();
        }
    }

    public ArrayList<String> saveAttributes() {
        ArrayList<String> list = new ArrayList<String>();

        list.add(name);
        list.add(type);
        list.add(Integer.toString(level));
        list.add(Integer.toString(experience));
        list.add(Integer.toString(attack));
        list.add(Integer.toString(defense));
        list.add(Integer.toString(hitPoints));

        return list;
    }

    public void printAttributes() {
        System.out.println("\nCharacter Stats");
        System.out.println("---------------");
        System.out.println("Name:       " + name);
        System.out.println("Class:      " + type);
        System.out.println("Level:      " + level);
        System.out.println("Experience: " + experience);
        System.out.println("Attack:     " + attack);
        System.out.println("Defense:    " + defense);
        System.out.println("Hit Points: " + hitPoints);
        System.out.println();
/*
        System.out.println("Character Artifacts");
        System.out.println("-------------------");
        System.out.println("Weapon: " );
        System.out.println("Armor: ");
        System.out.println("Helm: ");
*/
    }

    public void printControls() {
        System.out.println("\nControls");
        System.out.println("--------");
        System.out.println("W - Up");
        System.out.println("A - Left");
        System.out.println("S - Down");
        System.out.println("D - Right");

        System.out.println("Q - Main Menu");
        System.out.println("Z - Hero Stats");
        System.out.println("C - Controls");
        System.out.println();
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public  void setName(String name) {
        this.name = name;
    }
    public String getName() {
       return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;

    }
}
