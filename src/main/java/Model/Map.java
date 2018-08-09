package Model;

import java.util.Random;

public class Map {
    private int size;
    private Point[][] map;
    private Random rand = new Random();
    private Character character;

    public Map() {
        size = 0;
    }

    public Map(Character character) {
        this.size = (character.getLevel() - 1) * 5 + 10 - (character.getLevel() % 2);
        this.character = character;
        map = new Point[size][size];
        createMap();
    }

    private void createMap() {
        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                createGrid(x, y);
            }
        }
        character.setCharacterPosition(size / 2, size / 2);
    }

    private void createGrid(int x, int y) {

        //Create Villain depending on outcome of random, or place empty slot
        int placeVillain = rand.nextInt(5) + 1;
        if (placeVillain != 1 && placeVillain != 2) {
            if (x == 0)
                map[y][x] = new Point("|", "| ");
            else if (x == size - 1)
                map[y][x] = new Point("|", " |");
            else
                map[y][x] = new Point("|", " | ");
        } else {
            if (x == 0)
                map[y][x] = new Point(".", ". ");
            else if (x == size - 1)
                map[y][x] = new Point(".", " .");
            else
                map[y][x] = new Point(".", " . ");
        }
    }

/*
    private void createHero(int x, int y) {
        if (x == 0)
            map[y][x] = new Point("o", "o ");
        else if (x == size - 1)
            map[y][x] = new Point("o", " o");
        else
            map[y][x] = new Point("o", " o ");
    }
*/

    public void displayMap() {
        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                if (character.getX() == x && character.getY() == y) {
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
}
