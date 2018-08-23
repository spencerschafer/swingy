package Model;

import Model.Characters.Character;
import Model.Characters.Empty;
import Model.Characters.Hero;
import Model.Characters.Villain;

import java.util.Random;
import java.util.Scanner;

public class Map {
    private int size;
    private Character[][] map;
    private Random rand = new Random();
    private Hero hero;

    public Map() {
        size = 0;
    }

    public Map(Hero hero) {
        this.size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
        this.hero = hero;
        map = new Character[size][size];
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
                map[y][x] = new Empty(" . ");
            else if (x == 0)
                map[y][x] = new Villain("| ");
            else if (x == size - 1)
                map[y][x] = new Villain( " |");
            else
                map[y][x] = new Villain( " | ");

        } else {
            if (x == 0)
                map[y][x] = new Empty( ". ");
            else if (x == size - 1)
                map[y][x] = new Empty( " .");
            else
                map[y][x] = new Empty( " . ");
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
                System.out.print(map[y][x].getName());
            }
            System.out.println();
        }
    }

    public boolean battle() {

        Character villain = map[hero.getY()][hero.getX()];

        System.out.println(map[hero.getY()][hero.getX()].getClass().getName() + "\n");
        if (map[hero.getY()][hero.getX()].getClass().getSimpleName().equals("Villain")) {
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
                    return fight(hero, villain);
                case 2:
                    return run(hero, villain);
            }
        }
        return true;
    }

    private boolean fight(Character hero, Character villain) {
        int heroHitPoints = hero.getHitPoints();
        int heroDefense = hero.getDefense();
        int heroAttack = hero.getAttack();
        int heroDamageFactor;

        int villainHitPoints = villain.getHitPoints();
        int villainDefense = villain.getDefense();
        int villainAttack = villain.getAttack();
        int villainDamageFactor;

        while(heroHitPoints > 0 && villainHitPoints > 0) {
            System.out.println("\nSTART");
            System.out.println("h: " + heroHitPoints + " | " + heroDefense + " | " +  heroAttack );
            System.out.println("v: " + villainHitPoints + " | " + villainDefense + " | " +  villainAttack );
            heroDamageFactor = rand.nextInt(4) + 1;
            villainDamageFactor = rand.nextInt(4) + 1;

            villainHitPoints -= (heroAttack / heroDamageFactor);
            if (villainHitPoints < 0) {
                System.out.println("\nBattle won!\n");
                removeVillain();
                return true;
            }

            heroHitPoints -= (villainAttack / villainDamageFactor);
            if (heroHitPoints < 0) {
                System.out.println("\nBattle lost!\n");
                return false;
            }
            System.out.println("FINISH");
        }
        return true;
    }

    private boolean run(Character hero, Character villain) {
        int outcome = rand.nextInt(5) + 1;
        if (outcome != 1 && outcome != 2) {
            System.out.println("\nYou successfully evaded battle!\n");
            return true;
        } else {
            System.out.println("\nYou were unable to evade the battle.");
            System.out.println("Prepare to fight!");
            return fight(hero, villain);
        }
    }

    private void removeVillain() {
        if (hero.getX() == 0)
            map[hero.getY()][hero.getX()] = new Empty(". ");
        else if (hero.getX() == size - 1)
            map[hero.getY()][hero.getX()] = new Empty(" .");
        else
            map[hero.getY()][hero.getX()] = new Empty(" . ");
    }
}
