package View;

import Model.Map;
import Model.Character;
import Model.Point;
import Model.Simulation;

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
        Point point;
        Simulation simluate = new Simulation();

        map.displayMap();
        while (true)
        {
            System.out.println();
            character.move();
            if ((point = map.displayMap()) != null) {
                simluate.battle(character, point.getCharacter());
            }
        }
    }

// Note: call to GUI View method
   public void guiView(Character character) {
   }
}

