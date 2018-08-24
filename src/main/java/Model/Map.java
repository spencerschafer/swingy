package Model;

import Controller.Main;
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
                map[y][x] = new Villain("| ", hero);
            else if (x == size - 1)
                map[y][x] = new Villain(" |", hero);
            else
                map[y][x] = new Villain(" | ", hero);

        } else {
            if (x == 0)
                map[y][x] = new Empty(". ");
            else if (x == size - 1)
                map[y][x] = new Empty(" .");
            else
                map[y][x] = new Empty(" . ");
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
        int attack;
        int heroHitPoints = hero.getHitPoints();
        int villainHitPoints = villain.getHitPoints();

        System.out.println("\nHero     | Villain");
        System.out.println("--------------------");
        System.out.println("HP : " + hero.getHitPoints() + " | " + villain.getHitPoints());
        System.out.println("DEF:   " + hero.getDefense() + " | " + villain.getDefense());
        System.out.println("ATT:   " + hero.getAttack() + " | " + villain.getAttack());

        while (heroHitPoints > 0 && villainHitPoints > 0) {

//          hero's attack damage on villain
            attack = hero.getAttack() / ((rand.nextInt(5) + 1));
//            System.out.println("\nH HP: " + heroHitPoints + " | H ATT: " + attack);

            if ((villainHitPoints -= attack) <= 0) {
//                System.out.println("H HP: " + heroHitPoints);
//                System.out.println("V HP: " + villainHitPoints + "\n");
                System.out.println("\nBattle won!\n");
                this.hero.increaseExperience();
                removeVillain();
                return true;
            }

//          villain's attack damage on hero
            attack = villain.getAttack() / ((rand.nextInt(5) + 1));
            if ((rand.nextInt(20) + 1) == 1)
                attack *= 1.5;

//            System.out.println("V HP: " + villainHitPoints + " | V ATT: " + attack + "\n");
            if ((heroHitPoints -= attack) <= 0) {
//                System.out.println("H HP: " + heroHitPoints);
//                System.out.println("V HP: " + villainHitPoints + "\n");
                System.out.println("\nBattle lost!\n");
                Main.mainMenu();
            }
        }
        System.out.println("\n---\n");
        return true;
    }

    private boolean run(Character hero, Character villain) {
        int outcome = rand.nextInt(5) + 1;
        if (outcome != 1 && outcome != 2) {
            System.out.println("\nYou successfully evaded battle!\n");
            this.hero.setCharacterPosition(this.hero.getPreviousX(), this.hero.getPreviousY());
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

    public boolean victory() {
        if ((hero.getX() == 0) || (hero.getX() == (size - 1)) || (hero.getY() == 0) || (hero.getY() == (size - 1))) {
            System.out.println("Level Complete!\n");
            if (checkHeroLevel())
                Main.mainMenu();
            this.hero.levelUp();
            return true;
        }
        return false;
    }

    public boolean checkHeroLevel() {
        if (this.hero.getLevel() == this.hero.getMAXLEVEL()) {
            System.out.println("You have successfully defeated all levels.");
            System.out.println("Congratulations! You, " + hero.getName() + ", have ascended to LEGENDARY status!\n");
            return true;
        }
        else return false;
    }
}
