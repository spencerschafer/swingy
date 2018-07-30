package Model;

import java.util.Scanner;

public class Character {
    private int x;
    private int y;
    private int level;

    public Character(int level) {
        this.x = 0;
        this.x = 0;
        this.level = level;
    }

    public Character() {
        this.x = 0;
        this.y = 0;
        this.level = 0;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
