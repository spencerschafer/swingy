package Controller;

import Model.Character;
import View.View;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//    Reminder: When going back in main menu, methods are recalled, double check that recalling these methods do not create new instances of existing characters.
//    Reminder: User input validation not checked
//    Reminder: When loading character, it is assumed the file format (input) is correct as no validation has yet to be done on the file


public class Main {
    private static Character character = null;
    private static int selectView = 1;
    private static View view;

    public static void main(String[] args) {

        mainMenu();
    }

    public static void mainMenu() {
        int option;

        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1. Start");
        System.out.println("2. Select Character");
        System.out.println("3. Select View");
        System.out.println("\n0. Exit");
        System.out.println();
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
            System.out.println("\nNo character selected. Please select a character.");
            selectCharacter();
        } else {
            System.out.println("Game Started.");
            System.out.println("The character you selected is: \n");
            character.printAttributes();
            System.out.println("To view controls press the 'c' key.");
            character.printKey();

            view = new View(selectView, character);
        }
    }

    private static void selectCharacter() {
        int option;

        System.out.println("\nSelect Character");
        System.out.println("-------------");
        System.out.println("1. Create Character");
        System.out.println("2. Load Character");
        System.out.println("\n0. Cancel");
        System.out.println();
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
                System.out.println();
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
        System.out.println();
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
        System.out.println();
        option = scanner.nextInt();
        switch (option) {
            case 0:
                System.out.println();
                mainMenu();
                break;
            case 1:
                System.out.println("\nCharacter Created.");
                createCharacter(name, type);
                System.out.println();
                mainMenu();
                break;
            case 2:
                selectCharacter();
                break;
        }
    }

    //default create character method
    private static void createCharacter(String name, String type) {
        character = new Character(name, type);
    }

    private static void loadCharacter() {

        ArrayList<String> list = new ArrayList<String>();

        String fileName = System.getProperty("user.dir") + "/src/main/resources/saves/";
        File folder = new File(fileName);
        File[] listOfFiles = folder.listFiles();
        System.out.println("\nSaved Files");
        System.out.println("-----------");

        //TODO: check for exception
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
            System.out.println("\nThat file does not exist.");
            loadCharacter();
        }
        character = new Character(list);
        System.out.println("\nCharacter Loaded.\n");
        mainMenu();
    }

    private static void selectView() {
        int option;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSelect View");
        System.out.println("-----------");
        System.out.println("1. Console View");
        System.out.println("2. GUI View");
        System.out.println("\n0. Cancel");
        System.out.println();
        option = scanner.nextInt();

        switch (option) {
            case 0:
                System.out.println();
                mainMenu();
                break;
            case 1:
                selectView = 1;
                System.out.println("\nConsole View selected.\n");
                mainMenu();
                break;
            case 2:
                selectView = 2;
                System.out.println("\nGUI View selected.\n");
                mainMenu();
                break;
        }
    }

    //menu option 0
    private static void saveAndExit() {

        if (character != null) {

            int option;
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> list;

            System.out.println("\nSave Game");
            System.out.println("----------");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("\n0. Cancel");
            System.out.println();

            option = scanner.nextInt();
            switch (option) {
                case 0:
                    System.out.println();
                    mainMenu();
                    break;
                case 1:
                    try {
                        String textFile = character.getName() + "_" + character.getType() + "_" + character.getLevel() + "_" + character.getExperience() + ".txt";
                        String fileName = System.getProperty("user.dir") + "/src/main/resources/saves/" + textFile;
                        PrintWriter file = new PrintWriter(fileName);
                        list = character.saveAttributes();
                        for (String str : list)
                            file.println(str);
                        file.close();
                    } catch (FileNotFoundException e) {

                        //TODO
                        System.out.println("OOPS");
                    }
                    System.out.println("\nGame Saved.");
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }
}
