package Controller;

import Model.Character;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//NOTE: Reminder: When going back in main menu, methods are recalled, double check that recalling these methods do not create new instances of existing characters.
//NOTE: Reminder: Validation of user input has not been checked
//NOTE: Reminder: When loading character, it is assumed the file format (input) is correct as no validation has yet to be done on the file

//NOTE: Current goal: Create Character -> Save Character -> Load Character

public class Main {
    private static Character character = null;

    public static void main(String[] args) {

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
        if (character == null) {
            System.out.println("\nNo character selected. Please select a character.\n");
            mainMenu();
        } else {
            System.out.println("Game Started.");
            System.out.println("The character you selected is: \n");
            character.printAttributes();
        }
        mainMenu();
    }

    private static void selectCharacter() {
        int option;

        System.out.println("\nSelect Character");
        System.out.println("-------------");
        System.out.println("1. Create Character");
        System.out.println("2. Load Character");
        System.out.println("\n0. Cancel");
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();

        switch (option) {
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
        int option;
        String name;
        String type;

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter Character name: ");
        name = scanner.next();

//      perhaps only limit to 2 classes
        System.out.println("\nSelect Character type:  ");
        System.out.println("1. Warrior  ");
        System.out.println("2. Archer  ");
        System.out.println("3. Thief  ");
        System.out.println("4. Magician  ");
        option = scanner.nextInt();
        switch (option) {
            case 1:
                type = "Warrior";
                break;
            case 2:
                type = "Archer";
                break;
            case 3:
                type = "Thief";
                break;
            case 4:
                type = "Magician";
                break;
            default:
                type = "Default";
                break;
        }

        System.out.println("\nCreate Character '" + name + "' of type '" + type + "'?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("\n0. Cancel");
        option = scanner.nextInt();
        switch (option) {
            case 0:
                mainMenu();
                break;
            case 1:
                System.out.println("\nCharacter Created.\n");
                createCharacter(name, type, 2);
                mainMenu();
                break;
            case 2:
                selectCharacter();
                break;
        }
    }

    //temp method to allow modification of levels
    private static void createCharacter(String name, String type, int level) {
        character = new Character(name, type, 2);
    }

    //default create character method
    private static void createCharacter(String name, String type) {
        character = new Character();
    }

    private static void loadCharacter() {

        ArrayList<String> list = new ArrayList<String>();

        String fileName = System.getProperty("user.dir") + "/src/main/resources/saves/";
        File folder = new File(fileName);
        File[] listOfFiles = folder.listFiles();
        System.out.println("\nSaved Files");
        System.out.println("-----------");
        for (File file : listOfFiles) {
            System.out.println(file.getName());
        }

        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter the name of the file you would like to load:");
        String textFile = input.nextLine();
        fileName = System.getProperty("user.dir") + "/src/main/resources/saves/" + textFile;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("That file does not exist.");
            loadCharacter();
        }
        character = new Character(list);
        System.out.println("\nCharacter Loaded.\n");
        mainMenu();
    }

    private static void selectView() {
//      TODO
        System.out.println("Changed to GUI view.");
    }

    //NOTE: Menu Option 0
    private static void saveAndExit() {

        if (character != null) {

            int option;
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> list = new ArrayList<String>();

            System.out.println("Save Game");
            System.out.println("----------");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("\n0. Cancel");

            option = scanner.nextInt();
            switch (option) {
                case 0:
                    mainMenu();
                    break;
                case 1:
                    try {
                        String textFile = character.getName() + "_" + character.getType() + "_" + character.getLevel() + "_" + character.getExperience() + ".txt";
                        String fileName = System.getProperty("user.dir") + "/src/main/resources/saves/" + textFile;
                        PrintWriter file = new PrintWriter(fileName);
                        list = character.saveAttributes();
                        for (String str: list)
                            file.println(str);
                        file.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("OOPS");
                    }
                    System.out.println("\nGame Saved.");
                    break;
                case 2:
                    System.exit(1);
                    break;
            }
        }
    }
}
