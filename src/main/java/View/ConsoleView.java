package View;

import Controller.Main;
import Model.Characters.Hero;
import Model.Map;

public class ConsoleView {

    private Map map = null;
    private Hero hero = null;
    private boolean gameOn = true;

    public ConsoleView(Map map, Hero hero) {
        this.map = map;
        this.hero = hero;
        
        System.out.println("Game Started.");
        System.out.println("The hero you selected is: \n");
        hero.printAttributes();
        System.out.println("To view controls press the 'c' key.");
        hero.printKey();

        startGame();
        Main.setMap(this.map);
        Main.setHero(this.hero);
        Main.mainMenu();
    }
    
    private  void startGame() {
        while (gameOn) {
            map.displayMap();
            System.out.println();

            if (!hero.move()) {
                gameOn = false;
            }

            if (!map.battle()) {
                gameOn = false;
            }

            if (map.victory()) {
                
                map = new Map(hero);
            }
        }
        
    }
}
