package Controller;

import Model.Character;
import Model.Map;

public class Main {
    public static void main(String[] args) {
//        Character character = new Character(); // Model Character
        Character character = new Character(2); // Model Character
        Map map = new Map(character);
//        System.out.println("Character location is at (" + user.get_x() + ", " + user.get_y() + ").");

        map.drawMap(character);
//        System.out.println("x: " + character.getX() + "\ny: " + character.getY());
        while (true) {
            character.move();
//            System.out.println("1x: " + character.getX() + "\ny: " + character.getY());
            map.drawMap(character);
//            System.out.println("2x: " + character.getX() + "\ny: " + character.getY());
        }
    }


}