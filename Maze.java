import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Maze {
    private HashMap<String, String[]> adjacent = new HashMap<String, String[]>();
    private HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
    private ArrayList<int[]> drawCells = new ArrayList<int[]>();
    private Random rand = new Random();
    private int mazeSize;
    private int squareSize;
    private int offset;


    public Maze(int size, int squareSize, int offset) {
        this.squareSize = squareSize;
        this.mazeSize = (int)size/squareSize;
        this.offset = offset;

        for (int cols = 0; cols < mazeSize; cols++) {
            for (int rows = 0; rows < mazeSize; rows++) {
                String key = getCord(cols, rows);
                visited.put(key, false);
                adjacent.put(key, getAdjacent(cols, rows, mazeSize));
            }
        }

        String cursor = getCord(rand.nextInt(mazeSize), rand.nextInt(mazeSize));
        dfs(adjacent, visited, cursor, drawCells);
    }

    public ArrayList<int[]> getDrawCells() {
        return drawCells;
    }

    String getCord(int xPos, int yPos) {
        return String.valueOf(xPos + ":" + yPos);
    }

    void createDrawCells(String currPos, String nextPos) {
        String[] currPosSplit = currPos.split(":");
        int currXPos = Integer.parseInt(currPosSplit[0]);
        int currYPos = Integer.parseInt(currPosSplit[1]);  

        String[] nextPosSplit = nextPos.split(":");
        int nextXPos = Integer.parseInt(nextPosSplit[0]);
        int nextYPos = Integer.parseInt(nextPosSplit[1]);

        int directionX = (squareSize / 2) * (nextXPos - currXPos);
        int directionY = (squareSize / 2) * (nextYPos - currYPos);
        int[] currDraw = {(currXPos * squareSize) + offset, (currYPos * squareSize) + offset};
        int[] diffDraw = {(currXPos * squareSize) + (offset + directionX), (currYPos * squareSize) + (offset + directionY)};
        int[] nextDraw = {(nextXPos * squareSize) + offset, (nextYPos * squareSize) + offset};

        drawCells.add(currDraw);
        drawCells.add(diffDraw);
        drawCells.add(nextDraw);
    }

    String[] getAdjacent(int xPos, int yPos, int maxValue) {
        ArrayList<String> cordValues = new ArrayList<String>();
        if (xPos > 0)
            cordValues.add(getCord(xPos - 1, yPos));
        if (xPos < maxValue - 1)
            cordValues.add(getCord(xPos + 1, yPos));
        if (yPos > 0)
            cordValues.add(getCord(xPos, yPos - 1));
        if (yPos < maxValue - 1)
            cordValues.add(getCord(xPos, yPos + 1));

        String[] adjacent = new String[cordValues.size()];
        for (int i = 0; i < adjacent.length; i++) {
            int randPos = rand.nextInt(cordValues.size());
            adjacent[i] = cordValues.get(randPos);
            cordValues.remove(randPos);
        }

        return adjacent;
    }

    void dfs(HashMap<String, String[]> adjacent, HashMap<String, Boolean> visited, String cursor,
            ArrayList<int[]> drawCells) {
        visited.put(cursor, true);

        for (String i : adjacent.get(cursor)) {
            if (!visited.get(i)) {
                createDrawCells(cursor, i);
                dfs(adjacent, visited, i, drawCells);
            }
        }
    }
}