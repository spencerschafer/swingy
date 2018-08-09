package Model;

public class Point {
    private String character;
    private String mapCharacter;

    Point() {
        character = null;
        mapCharacter = null;
    }

    //Reminder: change String to Character to hold an actual character that will be retrieved in the battle simulation
    Point(String character, String mapCharacter) {
        this.character = character;
        this.mapCharacter = mapCharacter;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setMapCharacter(String mapCharacter) {
        this.character = mapCharacter;
    }

    public String getMapCharacter() {
        return mapCharacter;
    }
}
