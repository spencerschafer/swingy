package Model;

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

//  Artifacts
    Artifact Weapon = null; // increases attack
    Artifact Armor = null; // increases defense
    Artifact Helm = null; // increases hit points

//  Temporary level input to simulate character level from saved file
    public Character(String name, String type, int level){
        this.x = 0;
        this.x = 0;
        this.name = name;
        this.type = type;
        this.level = level;
    }

/*
    public Character(File file) {
        this.x = 0;
        this.y = 0;
        this.name = file.name;
        this.type = file.type;
        this.level = file.level;
    }
*/

//  New character will be level 0 by default
    public Character() {
        this.x = 0;
        this.y = 0;
        this.name = "Default";
        this.type = "Default";
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

    public void printAttributes() {
        System.out.println("Character Stats");
        System.out.println("---------------");
        System.out.println("Name:       " + name);
        System.out.println("Class:      " + type);
        System.out.println("Level:      " + level);
        System.out.println("Experience: " + experience);
        System.out.println("Attack:     " + attack);
        System.out.println("Defense:    " + defense);
        System.out.println("Hit Points: " + hitPoints);
/*
        System.out.println("Character Artifacts");
        System.out.println("-------------------");
        System.out.println("Weapon: " );
        System.out.println("Armor: ");
        System.out.println("Helm: ");
*/
    }
}
