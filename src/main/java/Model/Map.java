package Model;

import Model.Characters.Empty;
import Model.Characters.Hero;
import Model.Characters.Villain;

import java.util.Random;
import java.util.Scanner;

public class Map {
    private int size;
    private Point[][] map;
    private Random rand = new Random();
    private Hero hero;

    public Map() {
        size = 0;
    }

    public Map(Hero hero) {
        this.size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
        this.hero = hero;
        map = new Point[size][size];
        createMap();
    }

    private void createMap() {
        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                createGrid(x, y);
            }
        }
        hero.setCharacterPosition(size / 2, size / 2);
    }

    private void createGrid(int x, int y) {

        //Create Villain depending on outcome of random, or place empty slot
        int placeVillain = rand.nextInt(5) + 1;
        if (placeVillain != 1 && placeVillain != 2) {

            if ((x == size / 2) && (y == size / 2)) // Initial position of hero is on top of a hero resulting in a battle if no valid key is pressed
                map[y][x] = new Point(new Empty(), " . ");
            else if (x == 0)
                map[y][x] = new Point(new Villain(), "| ");
            else if (x == size - 1)
                map[y][x] = new Point(new Villain(), " |");
            else
                map[y][x] = new Point(new Villain(), " | ");

        } else {
            if (x == 0)
                map[y][x] = new Point(new Empty(), ". ");
            else if (x == size - 1)
                map[y][x] = new Point(new Empty(), " .");
            else
                map[y][x] = new Point(new Empty(), " . ");
        }
    }

    public void displayMap() {
        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                if (hero.getX() == x && hero.getY() == y) {
                    if (x == 0)
                        System.out.print("o ");
                    else if (x == size - 1)
                        System.out.print(" o");
                    else
                        System.out.print(" o ");
                    continue;
                }
                System.out.print(map[y][x].getMapCharacter());
            }
            System.out.println();
        }
    }

    public boolean battle() {
        if (map[hero.getY()][hero.getX()].getCharacter().getType().equalsIgnoreCase("Villain")) {
            int option;

            System.out.println("Villain Encountered!");
            System.out.println("--------------------");
            System.out.println("1. Fight");
            System.out.println("2. Run");

            Scanner scanner = new Scanner(System.in);
            System.out.println();
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    return fight();
                case 2:
                    return run();
            }
        }
        return true;
    }

    private boolean fight() {
        int outcome = rand.nextInt(5) + 1;
        if (outcome != 1 && outcome != 2) {
            System.out.println("\nBattle won!\n");
            //call to modify map square from villain to empty square
            removeVillain();
            return true;
        } else {
            System.out.println("\nBattle lost!\n");
            return false;
        }
    }

    private boolean run() {
        int outcome = rand.nextInt(5) + 1;
        if (outcome != 1 && outcome != 2) {
            System.out.println("\nYou successfully evaded battle!\n");
            return true;
        } else {
            System.out.println("\nYou were unable to evade the battle.");
            System.out.println("Prepare to fight!");
            return fight();
        }
    }

    private void removeVillain() {
        if (hero.getX() == 0)
            map[hero.getY()][hero.getX()] = new Point(new Hero("Empty", "Empty"), ". ");
        else if (hero.getX() == size - 1)
            map[hero.getY()][hero.getX()] = new Point(new Hero("Empty", "Empty"), " .");
        else
            map[hero.getY()][hero.getX()] = new Point(new Hero("Empty", "Empty"), " . ");
    }
}
