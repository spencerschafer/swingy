package Model;

import java.util.Random;

import static Controller.Main.mainMenu;

public class Simulation {

    //Reminder: This rand variable is just a temporary filler inplace of a battle simulation.
    private Random rand = new Random();

    private Character hero;
    private String villain;
//    private Character villain;

    //Reminder: String villain needs to be replaced with Character Villain
    public void battle(Character hero, String villain) {
        this.hero = hero;
        this.villain = villain;

        //Reminder: Remove this when a formula has been decided
        int outcome = rand.nextInt(5) + 1;

        if (outcome != 1 && outcome != 2) {
            System.out.println("\nBattle WON!");
            //call to modify map square from villain to empty square
        }
        else {
            System.out.println("\nYou were defeated.");
            System.out.println();
            mainMenu();
        }
    }
}
