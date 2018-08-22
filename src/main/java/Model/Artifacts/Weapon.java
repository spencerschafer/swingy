package Model.Artifacts;

public class Weapon {
    private int attack;

    public Weapon() {
        this.attack = 30;
    }

    public Weapon(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
