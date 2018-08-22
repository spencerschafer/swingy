package View;

import Model.Characters.Hero;
import Model.Map;

import static Controller.Main.mainMenu;

public class View {
    public View(int view, Hero character) {
        if (view == 1) {
            consoleView(character);
        } else if (view == 2) {
            guiView(character);
        }
    }

    private void consoleView(Hero character) {
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

    private void guiView(Hero character) {
    }


}

