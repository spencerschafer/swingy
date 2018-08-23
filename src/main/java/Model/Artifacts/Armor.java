package Model.Artifacts;


public class Armor {
    private int defense;

    public Armor() {
        this.defense = 10;
    }

    public Armor(int defense) {
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
