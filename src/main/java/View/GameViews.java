package View;

import Model.Characters.Hero;
import Model.Map;

import static Controller.Main.mainMenu;
import View.Gui.View;

public class GameViews {
    Map map;
    Hero hero;
    
    public GameViews(int view, Hero hero) {
        map = new Map(hero);
        this.hero = hero;
        
        if (view == 1) {
            consoleView();
        } else if (view == 2) {
            guiView();
        }
    }

    private void consoleView() {
            System.out.println("Game Started.");
            System.out.println("The hero you selected is: \n");
            hero.printAttributes();
            System.out.println("To view controls press the 'c' key.");
            hero.printKey();
            
        while (true) {
            map.displayMap();
            System.out.println();
            hero.move();
            map.battle();
            /*if (!map.battle()) {
                break;
            }*/

            if (map.victory()) {
                map = new Map(hero);
            }
        }
//        mainMenu();
    }

    private void guiView() {
        View view = new View(this.map, this.hero);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}

