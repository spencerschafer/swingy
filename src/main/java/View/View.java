package View;

import Model.Map;
import Model.Character;

public class View {
    //NOTE: call to Console View method
    public View(Character character) {
        Map map = new Map(character);
//        System.out.println("Character location is at (" + user.get_x() + ", " + user.get_y() + ").");

        map.drawMap(character);
//        System.out.println("x: " + character.getX() + "\ny: " + character.getY());
        while (true)

        {
            character.move();
//            System.out.println("1x: " + character.getX() + "\ny: " + character.getY());
            map.drawMap(character);
//            System.out.println("2x: " + character.getX() + "\ny: " + character.getY());
        }
    }

    //NOTE: call to GUI View method
   public View() {

   }
}

