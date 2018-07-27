package Controller;

import Model.Character;
import Model.Map;

public class Main {
    public static void main(String[] args) {
//        Character character = new Character(); // Model Character
        Character character = new Character(7); // Model Character
        Map map = new Map(character);
//        System.out.println("Character location is at (" + user.get_x() + ", " + user.get_y() + ").");
        map.drawMap();
    }


}