package Model;

public class Character {
    private int x;
    private int y;
    private int level;

    public Character (int level) {
        this.x = 0;
        this.x = 0;
        this.level = level;
    }

    public Character() {
        this.x = 0;
        this.y = 0;
        this.level = 0;
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
