package Model;

public class Point {
    private Character character;
    private String mapCharacter;

    Point() {
        character = null;
        mapCharacter = null;
    }

    Point(Character character, String mapCharacter) {
        this.character = character;
        this.mapCharacter = mapCharacter;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setMapCharacter(String mapCharacter) {
        this.mapCharacter = mapCharacter;
    }

    public String getMapCharacter() {
        return mapCharacter;
    }
}
