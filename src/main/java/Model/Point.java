package Model;

public class Point {
    String character;
    String mapCharacter;

    Point() {
        character = null;
        mapCharacter = null;
    }

    //Reminder: change String to Character to hold an actual character that will be retrieved in the battle simulation
    Point(String character, String mapChar) {
        this.character = character;
        this.mapCharacter = mapChar;
    }

    public String getCharacter() {
        return character;
    }

    public String getMapCharacter() {
        return mapCharacter;
    }
}
