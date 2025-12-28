import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Maze {
    private HashMap<String, String[]> adjacent = new HashMap<String, String[]>();
    private HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
    private ArrayList<int[]> drawCells = new ArrayList<int[]>();
    private Random rand = new Random();
    private int mazeSize;
    private int size;
    private int squareSize;
    private int offset;


    public Maze(int initSize, int initSquareSize, int initOffset) {
        this.size = initSize;
        this.squareSize = initSquareSize;
        this.mazeSize = (int)size/squareSize;
        this.offset = initOffset;

        for (int cols = 0; cols < mazeSize; cols++) {
            for (int rows = 0; rows < mazeSize; rows++) {
                String key = getCord(cols, rows);
                visited.put(key, false);
                adjacent.put(key, getAdjacent(cols, rows, mazeSize));
            }
        }

        String cursor = "0:0"; //getCord(rand.nextInt(mazeSize), rand.nextInt(mazeSize));
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

        System.out.println(currPos);
        System.out.println(nextPos);
        int directionX = (squareSize / 2) * (nextXPos - currXPos);
        // System.out.println(directionX);
        int directionY = (squareSize / 2) * (nextYPos - currYPos);
        // System.out.println(directionY);

        int[] currDraw = {(currXPos * squareSize) + offset, (currYPos * squareSize) + offset};
        int[] diffDraw = {(currXPos * squareSize) + (offset + directionX), (currYPos * squareSize) + (offset + directionY)};
        int[] nextDraw = {(nextXPos * squareSize) + offset, (nextYPos * squareSize) + offset};

        System.out.println(Arrays.toString(currDraw));
        System.out.println(Arrays.toString(diffDraw));
        System.out.println("-------------------");

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

        // System.out.println(cordValues);
        String[] adjacent = new String[cordValues.size()];
        for (int i = 0; i < adjacent.length; i++) {
            int randPos = rand.nextInt(cordValues.size());
            adjacent[i] = cordValues.get(randPos);
            // System.out.println(cordValues.get(randPos));
            cordValues.remove(randPos);
        }

        return adjacent;
    }

    void dfs(HashMap<String, String[]> adjacent, HashMap<String, Boolean> visited, String cursor,
            ArrayList<int[]> drawCells) {
        // drawCells.add(cursor);
        visited.put(cursor, true);

        for (String i : adjacent.get(cursor)) {
            // System.out.println(cursor);
            // System.out.println(i);
            if (!visited.get(i)) {
                createDrawCells(cursor, i);
                dfs(adjacent, visited, i, drawCells);
            }
        }
        // while (adjacent.get(cursor).length > 0) {
        //     int i = rand.nextInt(adjacent.get(cursor).length);
        //     if (!visited.get(i)) {
        //         dfs(adjacent, visited, adjacent.get(cu), res);
        //     }
        // }            
        // }
    }
}