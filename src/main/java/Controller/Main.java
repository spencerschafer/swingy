package Controller;

import Model.Character;
import Model.Map;

public class Main {
    public static void main(String[] args) {
        int mapSize = 6;
        Character character = new Character(mapSize); // Model Character
        Map map = new Map(mapSize, character);
//        System.out.println("Character location is at (" + user.get_x() + ", " + user.get_y() + ").");
        map.drawMap();
    }


}