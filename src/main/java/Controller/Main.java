package Controller;

import Model.Character;
import View.View;

import java.io.File;
import java.util.Scanner;

//NOTE: Reminder: When going back in main menu, methods are recalled, double check that recalling these methods do not create new instances of existing characters.

public class Main {
    public static void main(String[] args) {
        Character character = new Character(2); // Model Character

        mainMenu();
//        NOTE: Read from file and set appropriate settings

//        NOTE: Create new character
//        Character character = new Character(); // Model Character

//        NOTE: Create or load Map
//        View view = new View(character);

//        NOTE: Create or update Map
    }

    private static void mainMenu() {
        int option;

        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1. Start");
        System.out.println("2. Select Character");
        System.out.println("3. Select View");
        System.out.println("\n0. Exit");
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();

        switch (option) {
            case 1:
                startGame();
                break;
            case 2:
                selectCharacter();
                break;
            case 3:
                selectView();
                break;
            case 0:
                saveAndExit();
                break;
        }
    }

    private static void startGame() {
        //call map class here
        System.out.println("Game Started.");
    }

    private static void selectCharacter() {
        int Option;

        System.out.println("Select Character");
        System.out.println("-------------");
        System.out.println("1. Create Character");
        System.out.println("2. Load Character");
        System.out.println("\n0. Cancel");
        Scanner scanner = new Scanner(System.in);
        Option = scanner.nextInt();

        switch (Option) {
            case 1:
                newCharacter();
                break;
            case 2:
                loadCharacter();
                break;
            case 0:
                mainMenu();
                break;
        }
    }

    private static void newCharacter() {
        int Option;
        String Name;
        String Class;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Character Name: ");
        Name = scanner.next();

//      perhaps only limit to 2 classes
        System.out.println("Select Character Class:  ");
        System.out.println("1. Warrior  ");
        System.out.println("2. Archer  ");
        System.out.println("3. Thief  ");
        System.out.println("4. Magician  ");
        Option = scanner.nextInt();
        switch (Option) {
            case 1:
                Class = "Warrior";
                break;
            case 2:
                Class = "Archer";
                break;
            case 3:
                Class = "Thief";
                break;
            case 4:
                Class = "Magician";
                break;
            default:
                Class = "Default";
                break;
        }

        System.out.println("Create Character '" + Name + "' of Class '" + Class + "'?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("\n0. Cancel");
        Option = scanner.nextInt();
        switch (Option) {
            case 0:
                mainMenu();
                break;
            case 1:
                System.out.println("Character Created.");
//              TODO
//              play();
                break;
            case 2:
                newCharacter();
                break;
        }
    }

    private static void loadCharacter() {
//      type in file name/location
//      TODO
        System.out.println("Character Loaded.");
    }

    private static void selectView() {
//      TODO
        System.out.println("Changed to GUI view.");
    }

    //NOTE: Menu Option 0
    private static void saveAndExit() {
//      TODO

        System.out.println("Progress Saved.");
        System.exit(1);
    }
}
