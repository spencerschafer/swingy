package Model;

public class Map {

    private int size;
    private Character character;

    public Map (Character character) {
        this.size = (character.getLevel() - 1) * 5 + 10 - (character.getLevel() % 2);
        this.character = character;
    }

    public void drawMap() {
        setCharacterPos();
        for(int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                if (character.getX() == x && character.getY() == y) {
                    if (x == 0)
                        System.out.print("o ");
                    if (x == size - 1)
                        System.out.print(" o");
                    else
                        System.out.print(" o ");
                    continue;
                }

                if (x == 0)
                    System.out.print(". ");
                if (x == size - 1)
                    System.out.print(" .");
                else
                    System.out.print(" . ");
            }
            System.out.println();
        }
    }

    public void setCharacterPos() {
        this.character.setX(size / 2);
        this.character.setY(size / 2);
    }
}
