package Model;

public class Point {
    //TODO: String to Character
    private String character;
    private String mapCharacter;

    Point() {
        character = null;
        mapCharacter = null;
    }

    //TODO: String to Character
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
