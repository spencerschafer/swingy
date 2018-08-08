package Model;

import java.util.Random;

public class Map {

    private int size;
    private Character character;
    private Random rand = new Random();

    public Map (Character character) {
        this.size = (character.getLevel() - 1) * 5 + 10 - (character.getLevel() % 2);
        this.character = character;
        setCharacterPos();
    }

    public void drawMap() {
        if (character.getX() < 0)
            character.setX(0);
        if (character.getX() >= size)
            character.setX(size - 1);
        if (character.getY() < 0)
            character.setY(0);
        if (character.getY() >= size)
            character.setY(size - 1);
        for(int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                if (this.character.getX() == x && this.character.getY() == y) {
                    if (x == 0)
                        System.out.print("o ");
                    else
                        if (x == size - 1)
                        System.out.print(" o");
                    else
                        System.out.print(" o ");
                    continue;
                }

                int placeVillain = rand.nextInt(5) + 1;
                if (placeVillain != 1 && placeVillain != 2 ) {
                    if (x == 0)
                        System.out.print("v ");
                    else if (x == size - 1)
                        System.out.print(" v");
                    else
                        System.out.print(" v ");
                }
                else {
                    if (x == 0)
                        System.out.print(". ");
                    else if (x == size - 1)
                        System.out.print(" .");
                    else
                        System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setCharacterPos() {
        this.character.setX(size / 2);
        this.character.setY(size / 2);
    }
}
