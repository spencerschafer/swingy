package View;

import Model.Map;
import Model.Character;

import java.util.Random;

import static Controller.Main.mainMenu;

public class View {
    Random rand = new Random();

    //call to console view
    public View(int view, Character character) {
        if (view == 1) {
            consoleView(character);
        } else if (view == 2) {
            guiView(character);
        }
    }

    public void consoleView(Character character) {
        boolean alive = true;
        Map map = new Map(character);

        while (alive) {
            map.displayMap();
            System.out.println();
            character.move();
            if (!map.battle()) {
                break;
            }
        }
        mainMenu();
    }

    // Note: call to GUI View method
    public void guiView(Character character) {
    }


}

