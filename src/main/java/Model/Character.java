package Model;

public class Character {
    private int x;
    private int y;

    public Character(int mapSize) {
        // subtract 1 because map starts from 0
        if (mapSize % 2 != 0) {
            this.x = mapSize / 2;
            this.y = mapSize / 2;
        }
        else {
            this.x = mapSize / 2;
            this.y = mapSize / 2;
        }

    }

    public Character() {
        this.x = 0;
        this.y = 0;
    }

    public int get_x() {
        return x;
    }

    public void set_x(int x) {
        this.x = x;
    }

    public int get_y() {
        return y;
    }

    public void set_y(int y_pos) {
        this.y = y_pos;
    }
}
