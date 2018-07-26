package Model;

public class Map {

    private int size;
    private Character character;

    public Map (int size, Character character) {
        this.size = size;
        this.character = character;
    }

    public void drawMap() {
        for(int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                if (character.get_x() == x && character.get_y() == y) {
                    System.out.print("x");
                    continue;
                }
                System.out.print("o");
            }
            System.out.println();
        }
    }
}
