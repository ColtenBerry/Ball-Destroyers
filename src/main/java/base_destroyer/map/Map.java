package base_destroyer.map;

public class Map {
    private int width;
    private int height;
    private Cell[][] cells;
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = Cell.CLOSED;
            }
        }
    }
}
