import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Maze {
    private int mazeSize;
    private HashMap<String, String[]> adjacent = new HashMap<String, String[]>();
    private HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
    private ArrayList<String> res = new ArrayList<String>();
    private Random rand = new Random();

    public Maze(int newMazeSize) {
        this.mazeSize = newMazeSize;
    }

    public void createMaze() {
        for (int cols = 0; cols < mazeSize; cols++) {
            for (int rows = 0; rows < mazeSize; rows++) {
                String key = getCord(cols, rows);
                visited.put(key, false);
                adjacent.put(key, getAdjacent(cols, rows, mazeSize));
            }
        }

        String cursor = getCord(rand.nextInt(mazeSize), rand.nextInt(mazeSize));
        dfs(adjacent, visited, cursor, res);

    }

    public ArrayList<String> getRes() {
        return res;
    }

    String getCord(int xPos, int yPos) {
        return String.valueOf(xPos + ":" + yPos);
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
            // System.out.println(cordValues.get(randPos));
            cordValues.remove(randPos);
        }

        return adjacent;
    }

    void dfs(HashMap<String, String[]> adjacent, HashMap<String, Boolean> visited, String cursor,
            ArrayList<String> res) {
        res.add(cursor);
        visited.put(cursor, true);

        for (String i : adjacent.get(cursor)) {
            // System.out.println(cursor);
            // System.out.println(i);
            if (!visited.get(i)) {
                dfs(adjacent, visited, i, res);
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