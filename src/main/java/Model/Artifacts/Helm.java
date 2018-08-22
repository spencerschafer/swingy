package Model.Artifacts;

public class Helm {
    private int hitPoints;

    public Helm(){
        this.hitPoints = 10;
    }

    public Helm(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
