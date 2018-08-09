package View;

import Model.Map;
import Model.Character;
import Model.Point;

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
        Point point;

        map.displayMap();
        while (alive) {
            System.out.println();
            character.move();
            point = map.displayMap();
            if (point == null) {
                continue;
            }
            else {
                alive = battle(character, point.getCharacter());
                if (alive) {
                    map.displayMap();
                }
            }
        }
        mainMenu();
    }

    // Note: call to GUI View method
    public void guiView(Character character) {
    }


    //Reminder: String villain needs to be replaced with Character Villain
    public boolean battle(Character hero, String villain) {

        int outcome = rand.nextInt(5) + 1;

        System.out.println("\nVillain Encountered!");
        System.out.println("--------------------");
        System.out.println("1. Fight");
        System.out.println("2. Run");

        if (outcome != 1) {
            System.out.println("\nBattle WON!");
            System.out.println();
            //call to modify map square from villain to empty square
            return true;
        } else {
            System.out.println("\nYou were defeated.");
            System.out.println();
            return false;
        }
    }
}

