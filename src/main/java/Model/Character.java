package Model;

import apple.laf.JRSUIConstants;

import java.util.Scanner;

public class Character {
    private int x = 0;
    private int y = 0;


//  Attributes
    private String Name; // May not be relevant in all characters
    private String Class; // May not be relevant in all characters;
    private int Level = 0;
    private int Experience = 0;
    private int Attack = 30;
    private int Defense = 10;
    private int hitPoints = 100;

//  Artifacts
    Artifact Weapon = null; // increases attack
    Artifact Armor = null; // increases defense
    Artifact Helm = null; // increases hit points

//  Temporary level input to simulate character level from saved file
    public Character(int level) {
        this.x = 0;
        this.x = 0;
        this.Level = level;
    }

/*
    public Character(File file) {
        this.x = 0;
        this.y = 0;
        this.Level = file.Level;
    }
*/

//  New character will be level 0 by default
    Character() {
        this.x = 0;
        this.y = 0;
    }

    public void move() {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        if (str.equalsIgnoreCase("w")) {
            this.y -= 1;
        } else if (str.equalsIgnoreCase("s")) {
            this.y += 1;
        } else if (str.equalsIgnoreCase("a")) {
            this.x -= 1;
        } else if (str.equalsIgnoreCase("d")) {
            this.x += 1;
        }
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
        return Level;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public void printAttributes() {
        System.out.printf("Character Stats");
        System.out.printf("---------------");
        System.out.println("Name:" + Name);
        System.out.println("Class:" + Class);
        System.out.println("Level:" + Level);
        System.out.println("Experience:" + Experience);
        System.out.println("Attack:" + Attack);
        System.out.println("Defense:" + Defense);
        System.out.println("Hit Points:" + hitPoints);
/*
        System.out.println("Character Artifacts");
        System.out.println("-------------------");
        System.out.println("Weapon: " );
        System.out.println("Armor: ");
        System.out.println("Helm: ");
*/
    }
}
