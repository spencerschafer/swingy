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

        map.displayMap();
        while (true)
        {
            character.move();
            map.displayMap();
        }
    }

// Note: call to GUI View method
   public void guiView(Character character) {
   }
}

