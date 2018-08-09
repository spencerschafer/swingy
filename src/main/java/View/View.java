package View;

import Model.Map;
import Model.Character;

import static Controller.Main.mainMenu;

public class View {
    public View(int view, Character character) {
        if (view == 1) {
            consoleView(character);
        } else if (view == 2) {
            guiView(character);
        }
    }

    private void consoleView(Character character) {
        Map map = new Map(character);

        while (true) {
            map.displayMap();
            System.out.println();
            character.move();
            if (!map.battle()) {
                break;
            }
        }
        mainMenu();
    }

    private void guiView(Character character) {
    }


}

