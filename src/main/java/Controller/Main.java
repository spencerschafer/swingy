package Controller;

import Model.Characters.Hero;
import Model.Map;
import View.ConsoleView;
import View.GuiView;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static GuiView guiView = null;
    private static ConsoleView consoleView = null;
    private static Hero hero = null;
    private static Map map = null;

    public static void main(String[] args) {
        if (args.length == 0) {
            changeViewGui();
        } else if (args.length == 1) {
            if (args[0].equals("console")) {
                mainMenu();
            } else if (args[0].equals("gui")) {
                changeViewGui();
            }
        }
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
        if (scanner.hasNextInt()) {
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
                default:
                    System.out.println("\nIncorrect Value.\n");
                    mainMenu();
                    break;
            }
        } else {
            System.out.println("\nIncorrect Value.\n");
            mainMenu();
        }
    }

    private static void startGame() {

        if (hero == null) {
            System.out.println("\nNo hero selected. Please select a hero.");
            selectCharacter();
        } else {
            map = new Map(hero);
            changeViewConsole();
        }
    }

    private static void selectCharacter() {
        int option;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSelect Character");
        System.out.println("-------------");
        System.out.println("1. Create Character");
        System.out.println("2. Load Character");
        System.out.println("\n0. Cancel");
        System.out.println();

        if (scanner.hasNextInt()) {
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
                default:
                    System.out.println("\nIncorrect Value.");
                    selectCharacter();
                    break;
            }
        } else {
            System.out.println("\nIncorrect Value.");
            selectCharacter();
        }
    }

    private static void newCharacter() {
        int option;
        String name;
        String type = "Spence!";

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter Character name: ");
        name = scanner.next();

        System.out.println("\nSelect Character type:  ");
        System.out.println("1. Warrior  ");
        System.out.println("2. Archer  ");
        System.out.println();

        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    type = "Warrior";
                    break;
                case 2:
                    type = "Archer";
                    break;
                default:
                    System.out.println("\nIncorrect Value.");
                    newCharacter();
                    break;
            }
        } else {
            System.out.println("\nIncorrect Value.");
            newCharacter();
        }

        System.out.println("\nCreate Character '" + name + "' of type '" 
                + type + "'?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("\n0. Cancel");
        System.out.println();

        if (scanner.hasNextInt()) {
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
                default:
                    System.out.println("\nIncorrect Value.");
                    newCharacter();
                    break;
            }
        } else {
            System.out.println("\nIncorrect Value.");
            newCharacter();
        }
    }

    private static void createCharacter(String name, String type) {
        hero = new Hero(name, type);
    }

    private static void loadCharacter() {

        try {
        ArrayList<String> list = new ArrayList<>();

        String fileName = System.getProperty("user.dir") 
                + "/src/main/resources/saves/";
        File folder = new File(fileName);
        File[] listOfFiles = folder.listFiles();
        System.out.println("\nSaved Files");
        System.out.println("-----------");

        //TODO: check for exception
        for (File file : listOfFiles) {
            System.out.println(file.getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the name of the file "
                + "you would like to load:");
        String textFile = scanner.nextLine();
        fileName = System.getProperty("user.dir") + "/src/main/resources/saves/"
                + textFile;
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(new File(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("\nThat file does not exist.");
            loadCharacter();
        }
        hero = new Hero(list);
        System.out.println("\nCharacter Loaded.\n");
        mainMenu();
        } catch (Exception E) {
            System.out.println("\n No Saved Files Detected.\n");
            mainMenu();
        }
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

        if (scanner.hasNextInt()) {
            option = scanner.nextInt();

            switch (option) {
                case 0:
                    System.out.println();
                    mainMenu();
                    break;
                case 1:
                    System.out.println("\nConsole View selected.\n");
                    mainMenu();
                    break;
                case 2:
                    changeViewGui();
                    break;
                default:
                    System.out.println("\nIncorrect Value.");
                    selectView();
                    break;
            }
        } else {
            System.out.println("\nIncorrect Value.");
            selectView();
        }
    }

    private static void saveAndExit() {

        if (hero != null) {

            int option;
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> list;

            System.out.println("\nSave Game");
            System.out.println("----------");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("\n0. Cancel");
            System.out.println();

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                switch (option) {
                    case 0:
                        System.out.println();
                        mainMenu();
                        break;
                    case 1:
                        try {
                            String textFile = hero.getName() + "_" 
                                    + hero.getType() + "_"
                                    + hero.getLevel() + "_" 
                                    + hero.getExperience() + ".txt";
                            String fileName = System.getProperty("user.dir") +
                                    "/src/main/resources/saves/" + textFile;
                            PrintWriter file = new PrintWriter(fileName);
                            list = hero.saveAttributes();
                            for (String str : list) {
                                file.println(str);
                            }
                            file.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("No save directory. "
                                    + "Unable to save.");
                        }
                        System.out.println("\nGame Saved.");
                        break;
                    case 2:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nIncorrect Value.");
                        saveAndExit();
                        break;
                }
            } else {
                System.out.println("\nIncorrect Value.");
                saveAndExit();
            }
        }
    }

    public static void closeGui() {
        guiView.closeFrame();
        guiView = null;
        mainMenu();
    }
    public static void changeViewConsole() {
            consoleView = new ConsoleView(map, hero);
    }

    public static void changeViewGui() {
        guiView = new GuiView(map, hero);
    }
    
    public void loadMenu() {
        consoleView = null;
    }

    public static Hero getHero() {
        return hero;
    }

    public static void setHero(Hero hero) {
        Main.hero = hero;
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        Main.map = map;
    }
    
    
}
