package View;

import Model.Map;
import Model.Character;

public class View {
    //call to console view
    public View (int view, Character character) {
       if (view == 1) {
           consoleView(character);
       }
       else if (view == 2) {
           guiView(character);
       }
    }

    public void consoleView(Character character) {
        Map map = new Map(character);
//        System.out.println("Character location is at (" + user.get_x() + ", " + user.get_y() + ").");

        map.drawMap();
//        System.out.println("x: " + character.getX() + "\ny: " + character.getY());
        while (true)
        {
            character.move();
//            System.out.println("1x: " + character.getX() + "\ny: " + character.getY());
            map.drawMap();
//            System.out.println("2x: " + character.getX() + "\ny: " + character.getY());
        }
    }

    //NOTE: call to GUI View method
   public void guiView(Character character) {
   }
}

